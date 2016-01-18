package cn.letcode.utils.match;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 处理数据中参数
 * 
 * @author 帅军
 *
 */
public class MatchUtil {
	/**
	 * ${paramname}
	 */
	public static final Pattern patternParam = Pattern
			.compile("[$]\\{([\\w]+)\\}");

	/**
	 * <b>根据$ 匹配 $+{+参数名称+} ;</b><br>
	 * 根据参数名称获取参数 params中的值<br>
	 * <i>此功能可用作模板填充中</i>
	 * 
	 * @param s
	 *            元数据
	 * @param params
	 *            参数Map
	 * @return StringBuilder
	 */
	public static String matchValue(String s, Map<?, ?> params) {
		for (Matcher matcher = patternParam.matcher(s); matcher.find();) {
			int sub = matcher.groupCount();
			String n = matcher.group(sub).trim();
			String val = String.valueOf(params.get(matcher.group(sub).trim()));
			s = s.replace("${" + n + "}", val);
		}
		return s;
	}
}
