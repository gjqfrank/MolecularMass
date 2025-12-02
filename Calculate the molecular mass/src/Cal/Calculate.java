package Cal;

import java.util.*;

public class Calculate extends CalcutateMass {
    
    public static void main(String[] args) {
        while (true){
            System.out.println("请输入物质：");
            Scanner x =new Scanner(System.in);
            String substance = x.next(); 
            if (substance != "quit"){
                StringBuffer r1 = Calculate.dealWithfenhao(substance);
                StringBuffer r2 = Calculate.dealWithzhongkh(r1.toString());
                StringBuffer r3 = Calculate.dealWithxiaokh(r2.toString());
                Double result = Calculate.Ultcalc(r3.toString());
                System.out.println(result);
            }else if (substance == "quit"){
                x.close();
                break;
            }
            
            
        }
        

    }




}
