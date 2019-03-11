package com.hz.wsnIndoorBack.libSVMUtils.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:learning.properties")
@ConfigurationProperties(prefix="learning")
public class LearningParam {
	private String rootDir;//机器学习文件根目录
	private String higherAnchorHopUrl;//请求关键锚节点之间跳距的url
	private String normalAnchorHopUrl;//请求普通锚节点到关键锚节点之间跳距的url
	private String trainXPath;//训练数据的路径
	private String trainYPath;
	private String testXPath;//测试/预测数据的路径
	private String testYPath;
	private String modelXPath;//训练模型数据的路径
	private String modelYPath;
	private String outputXPath;//预测结果数据的路径
	private String outputYPath;
	private String resultXPath;//反归一化后最终结果的路径
	private String resultYPath;
	
	private String trainParams;//训练参数。格式见相关资料
	private String predicParams;//预测参数
	private int upper;//归一化:缩放到的区间最大值，一般为1
	private int lower;//归一化:缩放到的区间最小值，一般为0
	private int labelMinNum;//标签最小值，原始区间 TODO:从底图表获取
	private int labelMaxNum;//标签最大值，原始区间 
	private int attrMinNum;//属性最小值，原始区间 TODO:从数据中计算出
	private int attrMaxNum;//属性最小值，原始区间 
	
	//用于测试的时间戳
	private String testTimeStamp;
	
	//TODO:从数据库中读取网关的host而非配置文件
	private String testSinkHost;

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public String getHigherAnchorHopUrl() {
		return higherAnchorHopUrl;
	}

	public void setHigherAnchorHopUrl(String higherAnchorHopUrl) {
		this.higherAnchorHopUrl = higherAnchorHopUrl;
	}

	public String getNormalAnchorHopUrl() {
		return normalAnchorHopUrl;
	}

	public void setNormalAnchorHopUrl(String normalAnchorHopUrl) {
		this.normalAnchorHopUrl = normalAnchorHopUrl;
	}

	public String getTrainXPath() {
		return trainXPath;
	}

	public void setTrainXPath(String trainXPath) {
		this.trainXPath = trainXPath;
	}

	public String getTrainYPath() {
		return trainYPath;
	}

	public void setTrainYPath(String trainYPath) {
		this.trainYPath = trainYPath;
	}

	public String getTestXPath() {
		return testXPath;
	}

	public void setTestXPath(String testXPath) {
		this.testXPath = testXPath;
	}

	public String getTestYPath() {
		return testYPath;
	}

	public void setTestYPath(String testYPath) {
		this.testYPath = testYPath;
	}

	public String getModelXPath() {
		return modelXPath;
	}

	public void setModelXPath(String modelXPath) {
		this.modelXPath = modelXPath;
	}

	public String getModelYPath() {
		return modelYPath;
	}

	public void setModelYPath(String modelYPath) {
		this.modelYPath = modelYPath;
	}

	public String getOutputXPath() {
		return outputXPath;
	}

	public void setOutputXPath(String outputXPath) {
		this.outputXPath = outputXPath;
	}

	public String getOutputYPath() {
		return outputYPath;
	}

	public void setOutputYPath(String outputYPath) {
		this.outputYPath = outputYPath;
	}

	public String getResultXPath() {
		return resultXPath;
	}

	public void setResultXPath(String resultXPath) {
		this.resultXPath = resultXPath;
	}

	public String getResultYPath() {
		return resultYPath;
	}

	public void setResultYPath(String resultYPath) {
		this.resultYPath = resultYPath;
	}

	public String getTrainParams() {
		return trainParams;
	}

	public void setTrainParams(String trainParams) {
		this.trainParams = trainParams;
	}

	public String getPredicParams() {
		return predicParams;
	}

	public void setPredicParams(String predicParams) {
		this.predicParams = predicParams;
	}

	public int getUpper() {
		return upper;
	}

	public void setUpper(int upper) {
		this.upper = upper;
	}

	public int getLower() {
		return lower;
	}

	public void setLower(int lower) {
		this.lower = lower;
	}

	public int getLabelMinNum() {
		return labelMinNum;
	}

	public void setLabelMinNum(int labelMinNum) {
		this.labelMinNum = labelMinNum;
	}

	public int getLabelMaxNum() {
		return labelMaxNum;
	}

	public void setLabelMaxNum(int labelMaxNum) {
		this.labelMaxNum = labelMaxNum;
	}

	public int getAttrMinNum() {
		return attrMinNum;
	}

	public void setAttrMinNum(int attrMinNum) {
		this.attrMinNum = attrMinNum;
	}

	public int getAttrMaxNum() {
		return attrMaxNum;
	}

	public void setAttrMaxNum(int attrMaxNum) {
		this.attrMaxNum = attrMaxNum;
	}

	public String getTestTimeStamp() {
		return testTimeStamp;
	}

	public void setTestTimeStamp(String testTimeStamp) {
		this.testTimeStamp = testTimeStamp;
	}

	public String getTestSinkHost() {
		return testSinkHost;
	}

	public void setTestSinkHost(String testSinkHost) {
		this.testSinkHost = testSinkHost;
	}
}
