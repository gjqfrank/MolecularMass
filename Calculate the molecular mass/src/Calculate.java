import java.util.*;

public class Calculate{
    public static StringBuffer dealWithfenhao(String s2) {
        StringBuffer s1 = new StringBuffer();
        if (s2.indexOf(',') != -1) {
            String[] pts = s2.split(",");// 按·号分割
            List<String> parts = Arrays.asList(pts);
            for (String part : parts) {
                if (Character.isDigit(part.charAt(0)) == true) {
                    int len = part.length();
                    int count = 0;
                    for (int i = 0; i < len; i++) {
                        if (Character.isDigit(part.charAt(i)) == false) {
                            count = i;
                            break;
                        } else {
                            continue;
                        }
                    }
                    for (int a = 1; a <= Integer.parseInt(part.substring(0, count)); a++) {
                        s1.append(part.substring(count));
                    }
                } else {
                    s1.append(part);
                }
            }
            return s1;
        } else {
            return s1.append(s2);
        }
    }

    public static StringBuffer dealWithzhongkh(String s4) {
        StringBuffer s5 = new StringBuffer();
        if (s4.indexOf('[') != -1) {
            String[] ls2 = s4.split("\\[");
            List<String> ls = Arrays.asList(ls2);
            for (String ls1 : ls) {
                ls1 += " ";
                if (ls1.indexOf(']') == -1) {
                    s5.append(ls1);
                } else {
                    int endIndex = ls1.indexOf(']');
                    int i = endIndex + 1;
                    String current_substance = ls1.substring(0, endIndex);
                    int count = 0;
                    if (Character.isDigit(ls1.charAt(endIndex + 1)) == false) {
                        count = 1;
                    } else {

                        while (Character.isDigit(ls1.charAt(i)) == true) {
                            i++;
                        }
                        count = Integer.parseInt(ls1.substring(endIndex + 1, i));
                    }
                    for (int j = 1; j <= count; j++) {
                        s5.append(current_substance);
                    }
                    s5.append(ls1.substring(i));
                }
            }
            return s5;
        } else {
            return s5.append(s4);
        }
    }

    public static StringBuffer dealWithxiaokh(String s4) {
        StringBuffer s5 = new StringBuffer();
        String[] ls3 = s4.split("\\(");
        List<String> ls = Arrays.asList(ls3);
        for (String ls1 : ls) {
            ls1 += " ";
            if (ls1.indexOf(')') == -1) {
                s5.append(ls1);
            } else {
                int endIndex = ls1.indexOf(')');
                int i = endIndex + 1;
                String current_substance = ls1.substring(0, endIndex);
                int count = 0;
                if (Character.isDigit(ls1.charAt(endIndex + 1)) == false) {
                    count = 1;
                } else {

                    while (Character.isDigit(ls1.charAt(i)) == true) {
                        i++;
                    }
                    count = Integer.parseInt(ls1.substring(endIndex + 1, i));
                }
                for (int j = 1; j <= count; j++) {
                    s5.append(current_substance);
                }
                s5.append(ls1.substring(i));
            }
        }
        return s5;
    }

    public static double Ultcalc(String s) {
        final Map<String, Double> atomicWeights = new HashMap<>();

        // 添加 1~36 号元素（H~Kr）
        // 大部分元素是整数，但 Cl 是 35.5
        atomicWeights.put("H", 1.0); // 氢
        atomicWeights.put("He", 4.0); // 氦
        atomicWeights.put("Li", 7.0); // 锂
        atomicWeights.put("Be", 9.0); // 铍
        atomicWeights.put("B", 11.0); // 硼
        atomicWeights.put("C", 12.0); // 碳
        atomicWeights.put("N", 14.0); // 氮
        atomicWeights.put("O", 16.0); // 氧
        atomicWeights.put("F", 19.0); // 氟
        atomicWeights.put("Ne", 20.0); // 氖
        atomicWeights.put("Na", 23.0); // 钠
        atomicWeights.put("Mg", 24.0); // 镁
        atomicWeights.put("Al", 27.0); // 铝
        atomicWeights.put("Si", 28.0); // 硅
        atomicWeights.put("P", 31.0); // 磷
        atomicWeights.put("S", 32.0); // 硫
        atomicWeights.put("Cl", 35.5); // 氯（唯一的小数）
        atomicWeights.put("Ar", 40.0); // 氩
        atomicWeights.put("K", 39.0); // 钾
        atomicWeights.put("Ca", 40.0); // 钙
        atomicWeights.put("Sc", 45.0); // 钪
        atomicWeights.put("Ti", 48.0); // 钛
        atomicWeights.put("V", 51.0); // 钒
        atomicWeights.put("Cr", 52.0); // 铬
        atomicWeights.put("Mn", 55.0); // 锰
        atomicWeights.put("Fe", 56.0); // 铁
        atomicWeights.put("Co", 59.0); // 钴
        atomicWeights.put("Ni", 59.0); // 镍
        atomicWeights.put("Cu", 64.0); // 铜
        atomicWeights.put("Zn", 65.0); // 锌
        atomicWeights.put("Ga", 70.0); // 镓
        atomicWeights.put("Ge", 73.0); // 锗
        atomicWeights.put("As", 75.0); // 砷
        atomicWeights.put("Se", 79.0); // 硒
        atomicWeights.put("Br", 80.0); // 溴
        atomicWeights.put("Kr", 84.0); // 氪
        atomicWeights.put("I", 127.0); // 碘

        int len = s.length();
        double mass = 0.0;
        int number = 0;
        String current_element = " ";
        boolean foundElement = false; // 标记是否找到有效元素
        for (int k = 0; k < len; k++) {
            if (Character.isDigit(s.charAt(k)) == true || Character.isLowerCase(s.charAt(k)) == true
                    || Character.isWhitespace(s.charAt(k)) == true) {
                continue;
            } else {
                // 防止数组越界
                if (k + 1 >= len) {
                    // 最后一个字符是大写字母，代表单个元素
                    current_element = s.substring(k, k + 1);
                    if (!atomicWeights.containsKey(current_element)) {
                        return -1.0; // 无效元素
                    }
                    foundElement = true;
                    mass += atomicWeights.get(current_element);
                    continue;
                }

                if (Character.isLowerCase(s.charAt(k + 1)) == true) {
                    int start = k + 2;
                    int end = 0;
                    current_element = s.substring(k, k + 2);
                    // 检查元素是否有效
                    if (!atomicWeights.containsKey(current_element)) {
                        return -1.0; // 无效元素
                    }
                    foundElement = true;
                    if (k + 2 >= len || Character.isDigit(s.charAt(k + 2)) == false) {
                        number = 1;
                    } else {
                        for (int m = start; m < len; m++) {
                            if (Character.isAlphabetic(s.charAt(m)) == true
                                    || Character.isWhitespace(s.charAt(m)) == true) {
                                break;
                            }
                            end = m;
                        }
                        number = Integer.parseInt(s.substring(start, end + 1));
                    }

                    mass += number * atomicWeights.get(current_element);
                } else if (Character.isLowerCase(s.charAt(k + 1)) == false) {
                    int start = k + 1;
                    int end = 0;
                    current_element = s.substring(k, k + 1);
                    // 检查元素是否有效
                    if (!atomicWeights.containsKey(current_element)) {
                        return -1.0; // 无效元素
                    }
                    foundElement = true;
                    if (Character.isDigit(s.charAt(k + 1)) == false) {
                        number = 1;
                    } else {
                        for (int m = start; m < len; m++) {
                            if (Character.isAlphabetic(s.charAt(m)) == true
                                    || Character.isWhitespace(s.charAt(m)) == true) {
                                break;
                            }
                            end = m;
                        }
                        number = Integer.parseInt(s.substring(start, end + 1));
                    }

                    mass += number * atomicWeights.get(current_element);
                }
            }
        }
        // 如果没有找到任何有效元素，返回-1表示无效输入
        if (!foundElement) {
            return -1.0;
        }
        return mass;
    }
    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        while (true) {
            System.out.println("请输入物质：");
            String substance = x.next();
            if (substance.equals("quit")) {
                System.out.println("程序结束");
                break;
            }
            StringBuffer r1 = dealWithfenhao(substance);
            StringBuffer r2 = dealWithzhongkh(r1.toString());
            StringBuffer r3 = dealWithxiaokh(r2.toString());
            Double result = Ultcalc(r3.toString());
            if (result < 0) {
                System.out.println("输入的不是有效的化学物质，请重新输入。");
                continue;
            }
            System.out.println(result);
        }
        x.close();
    }
}