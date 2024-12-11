import java.util.Map;

/**
 * @description:
 * @author: oywl
 * @time: 2024-09-04 14:14
 */

public class Test_18 {
    public static String str =";\tNY48\t;\t2024-9-1\t;\n" +
            ";\tNY54\t;\t2024-9-1\t;\n" +
            ";\tN017\t;\t2024-9-1\t;\n" +
            ";\tPT06\t;\t2024-9-1\t;\n" +
            ";\tBZ09\t;\t2024-9-1\t;\n" +
            ";\tBZ33\t;\t2024-9-1\t;\n" +
            ";\tBZ35\t;\t2024-9-1\t;\n" +
            ";\tBZ10\t;\t2024-9-1\t;\n" +
            ";\tBZ17\t;\t2024-9-1\t;\n" +
            ";\tBZ25\t;\t2024-9-1\t;\n" +
            ";\tBZ42\t;\t2024-9-1\t;\n" +
            ";\tBZ13\t;\t2024-9-1\t;\n" +
            ";\tBZ05\t;\t2024-9-1\t;\n" +
            ";\tBZ07\t;\t2024-9-1\t;\n" +
            ";\tBZ08\t;\t2024-9-1\t;\n" +
            ";\tBZ02\t;\t2024-9-1\t;\n" +
            ";\tBZ24\t;\t2024-9-1\t;\n" +
            ";\tBZ23\t;\t2024-9-1\t;\n" +
            ";\tBZ26\t;\t2024-9-1\t;\n" +
            ";\tBZ19\t;\t2024-9-1\t;\n" +
            ";\tBZ20\t;\t2024-9-1\t;\n" +
            ";\tBZ34\t;\t2024-9-1\t;\n" +
            ";\tBZ15\t;\t2024-9-1\t;\n" +
            ";\tBZ36\t;\t2024-9-1\t;\n" +
            ";\tBZ37\t;\t2024-9-1\t;\n" +
            ";\tNF32\t;\t2024-9-1\t;\n" +
            ";\tBZ11\t;\t2024-9-1\t;\n" +
            ";\tBZ18\t;\t2024-9-1\t;\n" +
            ";\tBZ32\t;\t2024-9-1\t;\n" +
            ";\tNF29\t;\t2024-9-1\t;\n" +
            ";\tN054\t;\t2024-9-1\t;\n" +
            ";\tN003\t;\t2024-9-1\t;\n" +
            ";\tN003\t;\t2024-9-1\t;\n" +
            ";\tN066\t;\t2024-9-1\t;\n" +
            ";\tN066\t;\t2024-9-1\t;\n" +
            ";\tN069\t;\t2024-9-1\t;\n" +
            ";\tN069\t;\t2024-9-1\t;\n" +
            ";\tN070\t;\t2024-9-1\t;\n" +
            ";\tN039\t;\t2024-9-1\t;\n" +
            ";\tN039\t;\t2024-9-1\t;\n" +
            ";\tN039\t;\t2024-9-1\t;\n" +
            ";\tN024\t;\t2024-9-1\t;\n" +
            ";\tN024\t;\t2024-9-1\t;\n" +
            ";\tN067\t;\t2024-9-1\t;\n" +
            ";\tN053\t;\t2024-9-1\t;\n";
    public static void main(String[] args) {
//        System.out.println(str.replaceAll("\t",""));
        Map<String, String> envMap = System.getenv();
        envMap.entrySet().forEach(x-> System.out.println(x.getKey() + "=" + x.getValue()));
    }
}
