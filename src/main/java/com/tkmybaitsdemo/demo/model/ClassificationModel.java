package com.tkmybaitsdemo.demo.model;

import com.tkmybaitsdemo.demo.vo.ClassificationResultVO;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.jpmml.model.PMMLUtil;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;


/**
 * @author yb
 * @date 2023/03/271600
 **/

@Component
public class ClassificationModel {
    private Evaluator modelEvaluator;

    /**
     * 通过传入 PMML 文件路径来生成机器学习模型
     *
     * @param pmmlFileName pmml 文件路径
     */
    public ClassificationModel(String pmmlFileName) {
        PMML pmml = null;

        try {
            if (pmmlFileName != null) {
                InputStream is = new FileInputStream(pmmlFileName);
                pmml = PMMLUtil.unmarshal(is);
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("InputStream close error!");
                }

                ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();

                this.modelEvaluator = (Evaluator) modelEvaluatorFactory.newModelEvaluator(pmml);
                modelEvaluator.verify();
                System.out.println("加载模型成功！");
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    // 获取模型需要的特征名称
    public List<String> getFeatureNames() {
        List<String> featureNames = new ArrayList<String>();

        List<InputField> inputFields = modelEvaluator.getInputFields();

        for (InputField inputField : inputFields) {
            featureNames.add(inputField.getName().toString());
        }
        return featureNames;
    }

    // 获取目标字段名称
    public String getTargetName() {
        return modelEvaluator.getTargetFields().get(0).getName().toString();
    }

    // 使用模型生成概率分布
    private Map<String, ?> getProbabilityDistribution(Map<FieldName, ?> arguments) {
        Map<FieldName, ?> evaluateResult = modelEvaluator.evaluate(arguments);
//        Map<String, ?> resultRecord =
//        System.out.println(evaluateResult);
        return EvaluatorUtil.decode(evaluateResult);
    }
//
//    // 预测不同分类的概率
//    public ValueMap<String, Number> predictProba(Map<FieldName, Number> arguments) {
//        Map<String, ?> probabilityDistribution = getProbabilityDistribution(arguments);
//        return null;
//    }
//
//    // 预测结果分类
//    public Object predict(Map<FieldName, ?> arguments) {
//        Map<String, ?> probabilityDistribution = getProbabilityDistribution(arguments);
//
//        return probabilityDistribution.get("type");
//    }

    public static void main(String[] args) {
        ClassificationModel clf = new ClassificationModel("src/main/resources/XGBclassifier.pmml"); //这里模型地址
//0:benign 1:defacement 2:phishing 3:malware
        List<String> featureNames = clf.getFeatureNames();
        System.out.println("feature: " + featureNames);

        // 构建待预测数据
        Map<FieldName, Number> waitPreSample = new HashMap<>();   //这里的key一定要对应python中的列名
        waitPreSample.put(new FieldName("url_length"), 0);
        waitPreSample.put(new FieldName("number_of_letters"), 4/53);
        waitPreSample.put(new FieldName("number_of_digits"), 2);
        waitPreSample.put(new FieldName("dotcom"), 10);//符号总数
        waitPreSample.put(new FieldName("codot"), 0);
        waitPreSample.put(new FieldName("dotnet"), 3);
        waitPreSample.put(new FieldName("upper_case_number"), 40);//字母
        waitPreSample.put(new FieldName("lower_case_number"), 53);
        waitPreSample.put(new FieldName("dot_number"), 40);
        waitPreSample.put(new FieldName("dot_info_number"), 0);
        waitPreSample.put(new FieldName("www_dot_number"), 0);
        waitPreSample.put(new FieldName("not_alphanumeric_number"), 0);
        waitPreSample.put(new FieldName("percentage_number"), 0);
        waitPreSample.put(new FieldName("forwards_slash_to_length_ratio"), 4);
        waitPreSample.put(new FieldName("forward_slash"), 1);
        waitPreSample.put(new FieldName("percentage_length_ratio"), 0);


        System.out.println("waitPreSample predict result: " + clf.getProbabilityDistribution(waitPreSample));
//        System.out.println("waitPreSample predict result: " + clf.predict(waitPreSample).toString());
//        System.out.println("waitPreSample predictProba result: " + clf.predictProba(waitPreSample).toString());

    }


    public ClassificationResultVO predict(String urlString){
        ClassificationModel clf = new ClassificationModel("src/main/resources/XGBclassifier.pmml"); //这里模型地址
//0:benign 1:defacement 2:phishing 3:malware
        // 构建待预测数据
        Map<FieldName, Number> waitPreSample = new HashMap<>();
        waitPreSample.put(new FieldName("url_length"), urlString.length());

        String lettersOnly = urlString.replaceAll("[^a-zA-Z]", "");
        waitPreSample.put(new FieldName("number_of_letters"), lettersOnly.length());

        String digitsOnly = urlString.replaceAll("\\D", "");
        waitPreSample.put(new FieldName("number_of_digits"), digitsOnly.length());

        String[] dotcomOnly = urlString.split("\\.com");
        waitPreSample.put(new FieldName("dotcom"), dotcomOnly.length-1);

        String[] codotOnly = urlString.split("co\\.");
        waitPreSample.put(new FieldName("codot"), codotOnly.length-1);

        String[] dotnet = urlString.split("\\.net");
        waitPreSample.put(new FieldName("dotnet"), dotnet.length-1);

        String uppercaseOnly = urlString.replaceAll("[^A-Z]", "");
        waitPreSample.put(new FieldName("upper_case_number"), uppercaseOnly.length());

        String lowercaseOnly = urlString.replaceAll("[^a-z]", "");
        waitPreSample.put(new FieldName("lower_case_number"), lowercaseOnly.length());

        String[] dotOnly = urlString.split("\\.");
        waitPreSample.put(new FieldName("dot_number"), dotOnly.length-1);

        String[] dotInfoOnly = urlString.split("\\.info");
        waitPreSample.put(new FieldName("dot_info_number"), dotInfoOnly.length-1);

        String[] wwwdotOnly = urlString.split("www\\.");
        waitPreSample.put(new FieldName("www_dot_number"), wwwdotOnly.length-1);

        String symbolOnly = urlString.replaceAll("[^a-zA-Z0-9]", "");
        waitPreSample.put(new FieldName("not_alphanumeric_number"), symbolOnly.length());

        String[] percentageOnly = urlString.split("%");
        waitPreSample.put(new FieldName("percentage_number"), percentageOnly.length-1);
        waitPreSample.put(new FieldName("percentage_length_ratio"), (percentageOnly.length-1)/(double)urlString.length());


        String[] slashOnly = urlString.split("/");
        waitPreSample.put(new FieldName("forward_slash"), slashOnly.length-1);
        waitPreSample.put(new FieldName("forwards_slash_to_length_ratio"), (slashOnly.length-1)/(double)urlString.length());

        Map<String, ?> resultMap = clf.getProbabilityDistribution(waitPreSample);
        ClassificationResultVO resultVO = new ClassificationResultVO();
        Integer categoryNum = (Integer) resultMap.get("Category");
        switch(categoryNum) {
            case 0:resultVO.setCategory("benign");break;
            case 1:resultVO.setCategory("defacement");break;
            case 2:resultVO.setCategory("phishing");break;
            case 3:resultVO.setCategory("malware");break;
            default:resultVO.setCategory(null);break;
        }
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        resultVO.setProb0(df.format((float) resultMap.get("probability(0)")));
        resultVO.setProb1(df.format((float) resultMap.get("probability(1)")));
        resultVO.setProb2(df.format((float) resultMap.get("probability(2)")));
        resultVO.setProb3(df.format((float) resultMap.get("probability(3)")));
        return resultVO;
    }

}