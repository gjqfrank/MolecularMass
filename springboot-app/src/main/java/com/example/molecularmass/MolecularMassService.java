package com.example.molecularmass;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class MolecularMassService {

    private final Map<String, Double> atomicWeights;

    public MolecularMassService() {
        atomicWeights = new HashMap<>();
        // 添加元素原子质量
        atomicWeights.put("H", 1.0);
        atomicWeights.put("He", 4.0);
        atomicWeights.put("Li", 7.0);
        atomicWeights.put("Be", 9.0);
        atomicWeights.put("B", 11.0);
        atomicWeights.put("C", 12.0);
        atomicWeights.put("N", 14.0);
        atomicWeights.put("O", 16.0);
        atomicWeights.put("F", 19.0);
        atomicWeights.put("Ne", 20.0);
        atomicWeights.put("Na", 23.0);
        atomicWeights.put("Mg", 24.0);
        atomicWeights.put("Al", 27.0);
        atomicWeights.put("Si", 28.0);
        atomicWeights.put("P", 31.0);
        atomicWeights.put("S", 32.0);
        atomicWeights.put("Cl", 35.5);
        atomicWeights.put("Ar", 40.0);
        atomicWeights.put("K", 39.0);
        atomicWeights.put("Ca", 40.0);
        atomicWeights.put("Sc", 45.0);
        atomicWeights.put("Ti", 48.0);
        atomicWeights.put("V", 51.0);
        atomicWeights.put("Cr", 52.0);
        atomicWeights.put("Mn", 55.0);
        atomicWeights.put("Fe", 56.0);
        atomicWeights.put("Co", 59.0);
        atomicWeights.put("Ni", 59.0);
        atomicWeights.put("Cu", 64.0);
        atomicWeights.put("Zn", 65.0);
        atomicWeights.put("Ga", 70.0);
        atomicWeights.put("Ge", 73.0);
        atomicWeights.put("As", 75.0);
        atomicWeights.put("Se", 79.0);
        atomicWeights.put("Br", 80.0);
        atomicWeights.put("Kr", 84.0);
        atomicWeights.put("I", 127.0);
    }

    public double calculateMass(String formula) {
        // 处理各种格式
        String processed = dealWithComma(formula);
        processed = dealWithBrackets(processed);
        processed = dealWithParentheses(processed);

        return ultCalc(processed);
    }

    // 处理逗号分隔
    private String dealWithComma(String str) {
        if (!str.contains(",")) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        String[] parts = str.split(",");
        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (Character.isDigit(part.charAt(0))) {
                int count = 0;
                for (int i = 0; i < part.length(); i++) {
                    if (!Character.isDigit(part.charAt(i))) {
                        count = Integer.parseInt(part.substring(0, i));
                        String substance = part.substring(i);
                        for (int j = 0; j < count; j++) {
                            result.append(substance);
                        }
                        break;
                    }
                }
            } else {
                result.append(part);
            }
        }
        return result.toString();
    }

    // 处理中括号
    private String dealWithBrackets(String str) {
        if (!str.contains("[")) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        String[] parts = str.split("\\[");
        for (String part : parts) {
            if (!part.contains("]")) {
                result.append(part);
            } else {
                int endIdx = part.indexOf(']');
                String substance = part.substring(0, endIdx);
                StringBuilder numStr = new StringBuilder();
                int i = endIdx + 1;
                while (i < part.length() && Character.isDigit(part.charAt(i))) {
                    numStr.append(part.charAt(i));
                    i++;
                }
                int count = numStr.length() > 0 ? Integer.parseInt(numStr.toString()) : 1;
                for (int j = 0; j < count; j++) {
                    result.append(substance);
                }
                result.append(part.substring(i));
            }
        }
        return result.toString();
    }

    // 处理小括号
    private String dealWithParentheses(String str) {
        if (!str.contains("(")) {
            return str;
        }
        StringBuilder result = new StringBuilder();
        String[] parts = str.split("\\(");
        for (String part : parts) {
            if (!part.contains(")")) {
                result.append(part);
            } else {
                int endIdx = part.indexOf(')');
                String substance = part.substring(0, endIdx);
                StringBuilder numStr = new StringBuilder();
                int i = endIdx + 1;
                while (i < part.length() && Character.isDigit(part.charAt(i))) {
                    numStr.append(part.charAt(i));
                    i++;
                }
                int count = numStr.length() > 0 ? Integer.parseInt(numStr.toString()) : 1;
                for (int j = 0; j < count; j++) {
                    result.append(substance);
                }
                result.append(part.substring(i));
            }
        }
        return result.toString();
    }

    // 计算分子质量
    private double ultCalc(String s) {
        int len = s.length();
        double mass = 0.0;
        int number = 0;
        String currentElement;
        boolean foundElement = false;

        for (int k = 0; k < len; k++) {
            if (Character.isDigit(s.charAt(k)) || Character.isLowerCase(s.charAt(k)) 
                    || Character.isWhitespace(s.charAt(k))) {
                continue;
            }

            // 防止数组越界
            if (k + 1 >= len) {
                currentElement = s.substring(k, k + 1);
                if (!atomicWeights.containsKey(currentElement)) {
                    return -1.0;
                }
                foundElement = true;
                mass += atomicWeights.get(currentElement);
                continue;
            }

            if (Character.isLowerCase(s.charAt(k + 1))) {
                currentElement = s.substring(k, k + 2);
                if (!atomicWeights.containsKey(currentElement)) {
                    return -1.0;
                }
                foundElement = true;
                int start = k + 2;
                if (k + 2 >= len || !Character.isDigit(s.charAt(k + 2))) {
                    number = 1;
                } else {
                    int end = 0;
                    for (int m = start; m < len; m++) {
                        if (Character.isAlphabetic(s.charAt(m)) || Character.isWhitespace(s.charAt(m))) {
                            break;
                        }
                        end = m;
                    }
                    number = Integer.parseInt(s.substring(start, end + 1));
                }
                mass += number * atomicWeights.get(currentElement);
            } else {
                currentElement = s.substring(k, k + 1);
                if (!atomicWeights.containsKey(currentElement)) {
                    return -1.0;
                }
                foundElement = true;
                int start = k + 1;
                if (!Character.isDigit(s.charAt(k + 1))) {
                    number = 1;
                } else {
                    int end = 0;
                    for (int m = start; m < len; m++) {
                        if (Character.isAlphabetic(s.charAt(m)) || Character.isWhitespace(s.charAt(m))) {
                            break;
                        }
                        end = m;
                    }
                    number = Integer.parseInt(s.substring(start, end + 1));
                }
                mass += number * atomicWeights.get(currentElement);
            }
        }

        if (!foundElement) {
            return -1.0;
        }
        return mass;
    }
}