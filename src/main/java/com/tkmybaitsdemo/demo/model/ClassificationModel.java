package com.tkmybaitsdemo.demo.model;

import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.jpmml.model.PMMLUtil;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


/**
 * @author yb
 * @date 2023/03/271600
 **/


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
        ClassificationModel clf = new ClassificationModel("src/main/resources/decision_tree1.pmml"); //这里模型地址

        List<String> featureNames = clf.getFeatureNames();
        System.out.println("feature: " + featureNames);

        // 构建待预测数据
        Map<FieldName, Number> waitPreSample = new HashMap<>();   //这里的key一定要对应python中的列名
        //[count_of_www_dot, forwards_slash_to_length_ratio, count_of_dot, count_of_not_alphanumeric,
        // count_of_dotcom, number_of_digits, number_of_letters, length_of_url, count_of_lower_case,
        // count_of_dotnet, count_of_dot_info, count_of_codot, count_of_upper_case,
        // count_of_forward_slash, count_of_https, count_of_percentage, percentage_to_length_ratio]
        waitPreSample.put(new FieldName("count_of_www_dot"), 0);
        waitPreSample.put(new FieldName("forwards_slash_to_length_ratio"), 4/53);
        waitPreSample.put(new FieldName("count_of_dot"), 2);
        waitPreSample.put(new FieldName("count_of_not_alphanumeric"), 10);//符号总数
        waitPreSample.put(new FieldName("count_of_dotcom"), 0);
        waitPreSample.put(new FieldName("number_of_digits"), 3);
        waitPreSample.put(new FieldName("number_of_letters"), 40);//字母
        waitPreSample.put(new FieldName("length_of_url"), 53);
        waitPreSample.put(new FieldName("count_of_lower_case"), 40);
        waitPreSample.put(new FieldName("count_of_dotnet"), 0);
        waitPreSample.put(new FieldName("count_of_dot_info"), 0);
        waitPreSample.put(new FieldName("count_of_codot"), 0);
        waitPreSample.put(new FieldName("count_of_upper_case"), 0);
        waitPreSample.put(new FieldName("count_of_forward_slash"), 4);
        waitPreSample.put(new FieldName("count_of_https"), 1);
        waitPreSample.put(new FieldName("count_of_percentage"), 0);
        waitPreSample.put(new FieldName("percentage_to_length_ratio"), 0);


        System.out.println("waitPreSample predict result: " + clf.getProbabilityDistribution(waitPreSample));
//        System.out.println("waitPreSample predict result: " + clf.predict(waitPreSample).toString());
//        System.out.println("waitPreSample predictProba result: " + clf.predictProba(waitPreSample).toString());

    }

}