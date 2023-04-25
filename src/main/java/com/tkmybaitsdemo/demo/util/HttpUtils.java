package com.tkmybaitsdemo.demo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.IOException;
import java.net.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author yb
 * @date 2023/04/231545
 **/
public class HttpUtils {

    public static void main(String[] args) throws IOException, CertificateException {
        String url = "https://www.baidu.com";

        // 解析 URL 的主机名
        String host = new java.net.URL(url).getHost();

        // 查询主机名对应的 IP 地址
        InetAddress[] inetAddresses = InetAddress.getAllByName(host);

        System.out.println("IP addresses: ");
        for (InetAddress inetAddress : inetAddresses) {
            System.out.println(inetAddress.getHostAddress());
        }

        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()))
                .build();

        Mono<ClientResponse> responseMono = webClient.get()
                .uri(url)
                .exchange();

        responseMono.subscribe(response -> {
            String server = response.headers().header("Server").get(0);
            System.out.println("Server: " + server);
        });

        Certificate[] certificates = getCertificatesFromUrl(url);

        if (certificates != null) {
            for (int i = 0; i < certificates.length; i++) {
                System.out.println("Certificate " + (i + 1) + ": ");
                System.out.println("Type: " + certificates[i].getType());
//                System.out.println("Encoded: " + encodeCertificate(certificates[i]));
                System.out.println("Valid From: " + ((X509Certificate) certificates[i]).getNotBefore());
                System.out.println("Valid Until: " + ((X509Certificate) certificates[i]).getNotAfter());
                System.out.println("-----------------------");
            }
        } else {
            System.out.println("Failed to retrieve certificates.");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getRedirectUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(false);
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode >= 300 && responseCode < 400) {
            String redirectUrl = connection.getHeaderField("Location");
            return redirectUrl;
        } else {
            return null;
        }
    }

    private static Certificate[] getCertificatesFromUrl(String urlString) throws IOException, CertificateException {
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.connect();

        try {
            return connection.getServerCertificates();
        } catch (SSLPeerUnverifiedException e) {
            System.err.println("Failed to get server certificates: " + e.getMessage());
            return null;
        }
    }

    private static String encodeCertificate(Certificate certificate) throws CertificateEncodingException {
        return javax.xml.bind.DatatypeConverter.printBase64Binary(certificate.getEncoded());
    }
}
