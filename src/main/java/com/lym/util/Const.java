package com.lym.util;

import java.util.LinkedHashMap;

/**
 * @ClassName DistributedUniqueNoServiceImpl
 * @Description 公共常量类
 * @Author LYM
 * @Date 2018/8/6 11:02
 * @Version 1.0
 */
public class Const {

	/*****************************************错误代码开始*****************************************/
	/**成功**/
	public static final String ERR_CODE_SUCCESS = "00000";
	/**系统错误**/
	public static final String ERR_CODE_SYS_ERR = "99999";
	/*****************************************错误代码结束*****************************************/

	/**业务类型**/
	public static final String MSG_BIZ_TAG = "bizTag";
	/**缓存区1**/
	public static final String LEAF_SEGMENT_1 = "leaf_segment_1";
	/**缓存区2**/
	public static final String LEAF_SEGMENT_2 = "leaf_segment_2";
	/**最大ID**/
	public static final String MAX_ID = "maxId";
	/**当前ID**/
	public static final String CURRENT_ID = "currentId";
	/**步长**/
	public static final String STEP = "step";



	public static final LinkedHashMap<String,String> codeMap = new LinkedHashMap<String,String>(){
		private static final long serialVersionUID = 5921168460518588271L;
		{
			put(ERR_CODE_SUCCESS, "成功");
			put(ERR_CODE_SYS_ERR,"系统异常");
		}
	};

	/**
	 * @param key
	 * @return
	 */
	public static String getMsg(String key) {
		String value = Const.codeMap.containsKey(key) ? Const.codeMap.get(key) : "未知错误";
		return value;
	}

}
