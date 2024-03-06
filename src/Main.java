import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static ArrayList<Integer> master = new ArrayList<Integer>();
    static int idx;

    public static void main(String[] args) throws IOException {
        File in = new File("in.txt");
        BufferedReader br = new BufferedReader(new FileReader(in));
        idx = 0;
        master.add(0);
        parse(br);
        br.close();
    }

    public static void parse(BufferedReader br) throws IOException {
        char c = (char)br.read();
        while(c != ':') {
            switch(c) {
                case '>':
                    c = (char)br.read();
                    int inc = 0;
                    if(c == '[') {
                        while((c = (char)br.read())!=']') {
                            if(c == '.') inc = inc * 10 + master.get(idx);
                            else inc = inc * 10 + (int)(c-'0');
                        }
                        c = (char)br.read();
                    } else {
                        inc = 1;
                    }
                    idx += inc;
                    if(master.size()<=idx) {
                        for(int i = master.size(); i < idx+1; i++) {
                            master.add(0);
                        }
                    }
                    break;
                case '<':
                    c = (char)br.read();
                    inc = 0;
                    if(c == '[') {
                        while((c = (char)br.read())!=']') {
                            if(c == '.') inc = inc * 10 + master.get(idx);
                            else inc = inc * 10 + (int)(c-'0');
                        }
                        c = (char)br.read();
                    } else {
                        inc = 1;
                    }
                    idx -= inc;
                    if(idx<0) idx = 0;
                    break;
                case '_':
                    idx = 0;
                    c = (char)br.read();
                    break;
                case '+':
                    c = (char)br.read();
                    inc = 0;
                    if(c == '[') {
                        while((c = (char)br.read())!=']') {
                            if(c == '.') inc = inc * 10 + master.get(idx);
                            else inc = inc * 10 + (int)(c-'0');
                        }
                        c = (char)br.read();
                    } else {
                        inc = 1;
                    }
                    master.set(idx, master.get(idx) + inc);
                    break;
                case '-':
                    c = (char)br.read();
                    inc = 0;
                    if(c == '[') {
                        while((c = (char)br.read())!=']') {
                            if(c == '.') inc = inc * 10 + master.get(idx);
                            else inc = inc * 10 + (int)(c-'0');
                        }
                        c = (char)br.read();
                    } else {
                        inc = 1;
                    }
                    master.set(idx, master.get(idx) - inc);
                    break;
                case '*':
                    System.out.print((char)master.get(idx).intValue());
                    c = (char)br.read();
                    break;
                case '^':
                    Scanner sc = new Scanner(System.in);
                    master.set(idx, master.get(idx) + sc.nextInt());
                    c = (char)br.read();
                    break;
                case '=':
                    if(master.get(idx)!=0) {
                        while(c != '|') c = (char)br.read();
                    }
                    c = (char)br.read();
                    break;
                case '@':
                    StringBuilder chars = new StringBuilder();
                    while((c = (char)br.read()) != '}') {
                        chars.append(c);
                    }
                    File temp = new File("temp\\" + master.get(idx) + ".txt");
                    temp.deleteOnExit();
                    BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                    bw.write(chars.toString());
                    bw.flush();
                    bw.close();
                    c = (char)br.read();
                    break;
                case '#':
                    BufferedReader b = new BufferedReader(new FileReader("temp\\" + master.get(idx) + ".txt"));
                    parse(b);
                    b.close();
                    c = (char)br.read();
                    break;
                case '$':
                    System.out.println("DEBUG: value " + master.get(idx) +  " at idx " + idx );
                    c = (char)br.read();
                    break;
                case '?':
                    System.out.println("DEBUG: master array: {" + master.stream().map(Object::toString)
                            .collect(Collectors.joining(", ")) + "}");
                    c = (char)br.read();
                    break;
                default:
                    c = (char)br.read();
                    break;
            }
        }
    }

}