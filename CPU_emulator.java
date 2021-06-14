import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class CPU_emulator {

    static int ac;
    static int pc = 0;
    static int ax;

    static int flag = 0;
    static String methodDecision;
    static int variableDecision;

    static String okunan;

    static HashMap<Integer, Integer> mapAc = new HashMap<>();//<ulaşılmaya çalışılan değerin indisi , ac değeri>

    static ArrayList<Integer> splitted0 = new ArrayList<Integer>();//program.txt okunan satırın ilk değerleri(pc değerleri)
    static ArrayList<String> splitted1 = new ArrayList<String>();//program.txt okunan satırın 2. değerleri (methodName)
    static ArrayList<String> splitted2 = new ArrayList<String>();//program.txt okunan satırın 3. değerleri (işleme alınacak değer)


    public static void main(String[] args) {

        try {
            String fileName = "C:\\Users\\Lenovo\\Desktop\\orgSon.txt";
            File file = new File(fileName);
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String readLine = "";

            while ((readLine = buff.readLine()) != null) {//okunabilirlik açısından kolay olması için if'ler || ile birleştirilmedi
                okunan = readLine;
                String[] splittedArray = okunan.split(" ", 3);
                if (splittedArray[1].trim().equalsIgnoreCase("START")) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("HALT"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");
                    break;

                } else if ((splittedArray[1].trim().equalsIgnoreCase("DISP"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("SWAP"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("STOREI"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("LOADI"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("POP"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("RETURN"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else if ((splittedArray[1].trim().equalsIgnoreCase("DASC"))) {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add("null");

                } else {
                    splitted0.add(Integer.parseInt(okunan.split(" ")[0].trim()));
                    splitted1.add(okunan.split(" ")[1]);
                    splitted2.add(okunan.split(" ")[2].trim());
                }

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        sendToDecision(pc);
       
    }

    /*
    program akışını sağlamak için gerekli methodlar
     */
    public static void sendToDecision(int pc) {//jump işlemlerini sağlamak ve jump olmadığı taktirde programı normal akışında devam ettirmek için(pc parametresi artırılarak methdolardan geri yollanıyor)
        int i = pc;

        if (splitted2.get(i).equalsIgnoreCase("null")) {
            //System.out.println("PC DEĞERİ " + pc);//deneme
            decision2(splitted1.get(i));
        } else {
            //System.out.println("PC DEĞERİ " + pc);//deneme
            decision1(splitted1.get(i), splitted2.get(i));
        }
    }

    public static void decision1(String string1, String string2) {// 3 değerli (Integer(pc değeri) - String(methodName) - Integer(işleme alınacak sayı)) okunanlar
        methodDecision = string1.toUpperCase().trim();
        variableDecision = Integer.parseInt(string2);
        switch (methodDecision) {

            case "LOAD":
                load(variableDecision);
                break;
            case "LOADM":
                loadm(variableDecision);
                break;
            case "STORE":
                store(variableDecision);
                break;
            case "CMPM":
                cmpm(variableDecision);
                break;
            case "CJMP":
                cjmp(variableDecision);
                break;
            case "JMP":
                jmp(variableDecision);
                break;
            case "ADD":
                add(variableDecision);
                break;
            case "ADDM":
                addm(variableDecision);
                break;
            case "SUBM":
                subm(variableDecision);
                break;
            case "SUB":
                sub(variableDecision);
                break;
            case "MUL":
                mul(variableDecision);
                break;
            case "MULM":
                mulm(variableDecision);
                break;
            case "PUSH":
                push(variableDecision);
                break;

        }
    }

    public static void decision2(String string1) {// 2 değerli (Integer(pc değeri) - String(methodName) olanlar için)
        methodDecision = string1.toUpperCase().trim();
        switch (methodDecision) {
            case "START":
                start();
                break;
            case "HALT":
                halt();
                break;
            case "DISP":
                disp();
                break;
            case "POP":
                pop();
                break;
            case "RETURN":
                returnMethod();
                break;
            case "LOADI":
                loadı();
                break;
            case "STOREI":
                storeı();
                break;
            case "DASC":
                dasc();
                break;
            /*case "ADDTORESULTARRAY"://disp yerine disp2() kullanılmasına izin verildiği taktirde kullanılır
                toSaveResult();
                break;*/
            case "SWAP":
                swap();
                break;

        }
    }

    /*
    program işlemleri
     */
    public static void start() {
        System.out.println("Program başlatıldı(start komutu işleme alındı)");
        sendToDecision(++pc);
    }

    public static void load(int param) {
        ac = param;
        sendToDecision(++pc);
    }

    public static void loadm(int param) {
        ac = mapAc.get(param);
        sendToDecision(++pc);
    }

    public static void loadı() {
        ac = mapAc.get(ax);
        sendToDecision(++pc);
    }

    public static void storeı() {
        mapAc.put(ax, ac);
        sendToDecision(++pc);
    }

    public static void store(int param) {
        mapAc.put(param, ac);
        sendToDecision(++pc);

    }

    public static void cmpm(int param) {
        if (ac > mapAc.get(param)) {
            flag = 1;
        } else if (ac < mapAc.get(param)) {
            flag = -1;
        } else {
            flag = 0;
        }
        sendToDecision(++pc);

    }

    public static void cjmp(int param) {
        if (flag > 0) {
            pc = param;
            sendToDecision(pc);
        } else {
            pc = pc;
            sendToDecision(++pc);
        }

    }

    public static void jmp(int param) {
        pc = param;
        sendToDecision(pc);

    }

    public static void add(int param) {
        ac += param;
        sendToDecision(++pc);

    }

    public static void addm(int param) {
        ac += mapAc.get(param);
        sendToDecision(++pc);

    }

    public static void subm(int param) {
        ac -= mapAc.get(param);
        sendToDecision(++pc);

    }

    public static void sub(int param) {
        ac -= param;
        sendToDecision(++pc);

    }

    public static void mul(int param) {
        ac *= param;
        sendToDecision(++pc);

    }

    public static void mulm(int param) {
        ac *= mapAc.get(param);
        sendToDecision(++pc);

    }

    public static void push(int param) {
        stack.push(param);
        sendToDecision(++pc);

    }

    public static void pop() {
        ac = stack.pop();
        sendToDecision(++pc);

    }

    public static void returnMethod() {
        pc = stack.pop();
        sendToDecision(pc);

    }

    public static void dasc() {

    }

    public static void swap() {
        int swapAc = ac;//eşitlenecek değerin kaybolmaması için değişkene atandı
        int swapAx = ax;//eşitlenecek değerin kaybolmaması için değişkene atandı
        ac = swapAx;
        ax = swapAc;
        sendToDecision(++pc);
    }

    public static void disp() {
        System.out.println(ac);
        sendToDecision(++pc);
    }

 
    public static void halt() {
        System.out.println("program durduruldu(halt komutu işleme alındı)");

    }

}