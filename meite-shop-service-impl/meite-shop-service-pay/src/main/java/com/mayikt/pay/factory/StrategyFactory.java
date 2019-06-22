package com.mayikt.pay.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;

import com.mayikt.pay.strategy.PayStrategy;

/**
 * @description: 初始化不同的策略行为
 */
public class StrategyFactory {

	private static StrategyFactory instance = new StrategyFactory();
	private static Map<String, PayStrategy> strategyBean;
	private StrategyFactory(){
		strategyBean = new ConcurrentHashMap<String, PayStrategy>();
	}

	public static StrategyFactory getInstance(){
		if (instance == null) {
			instance = new StrategyFactory();
		}
		return instance;
	}

	// 思考几个点：
	public static PayStrategy getPayStrategy(String classAddres) {
		try {
			if (StringUtils.isEmpty(classAddres)) {
				return null;
			}
			PayStrategy beanPayStrategy = strategyBean.get(classAddres);
			if (beanPayStrategy != null) {
				return beanPayStrategy;
			}
			// 1.使用Java的反射机制初始化子类
			Class<?> forName = Class.forName(classAddres);
			// 2.反射机制初始化对象
			PayStrategy payStrategy = (PayStrategy) forName.newInstance();
			if (null!= payStrategy){
				strategyBean.put(classAddres, payStrategy);
			}
			return payStrategy;
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		PayStrategy payStrategy = StrategyFactory.getPayStrategy("com.mayikt.pay.strategy.impl.AliPayStrategy");
		System.out.println("payStrategy = " + payStrategy);
	}

}
