package com.practical;

import java.util.*;

public class Main {
    public static void main(String[] args){

        //GenericsPractice s = new GenericsPractice();
        Scanner scan = new Scanner(System.in);

        int n = 100;
        while(n>0) {
            System.out.print("Введите значение для reverse: ");
            String rev = scan.nextLine();
            System.out.println("1.Метод двух точек \n2.Через стэк \n3.Через Индексы \n4.Через deque \n5.Через AraryL\n");
            System.out.println("Выберите метод для reverse: ");

            int option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Принимаемой значение: " + rev);
                    System.out.println("Перевернутое значение: ");
                    System.out.print(getTwoPointersReverse(rev) + " \n");
                    break;
                case 2:
                    System.out.println("Принимаемой значение: " + rev);
                    System.out.println("Перевернутое значение: ");
                    System.out.println(getStackReverse(rev) + " \n");
                    break;
                case 3:
                    System.out.println("Принимаемой значение: " + rev);
                    System.out.println("Перевернутое значение: ");
                    System.out.println(getIndexesReverse(rev) + " \n");
                    break;
                case 4:
                    System.out.println("Принимаемой значение: " + rev);
                    System.out.println("Перевернутое значение: ");
                    System.out.println(getDequeReverse(rev) + " \n");
                    break;
                case 5:
                    System.out.println("Принимаемой значение: " + rev);
                    System.out.println("Перевернутое значение: ");
                    System.out.println(getArrayLReverse(rev) + " \n");
                    break;
                default:
                    System.out.println("OUT");

            }
            n--;
        }
    }

    /*проходимся с двух сторон, если цифра то просто перешагиваем, если нет то меням местами*/
    public static  String getTwoPointersReverse(String rev){
            char[] chars = rev.toCharArray();
            int left = 0;
            int right = chars.length - 1;

            while (left<right){
                if(Character.isDigit(chars[left])){
                    left++;
                    continue;
                }
                if(Character.isDigit(chars[right])){
                    right--;
                    continue;
                }
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;

                left++;
                right--;
            }

            return new String(chars);
    }

    /*Стек идет по алгоритму LIFO, если не цифра то добавляем его в стек, после собираем результат - если цифра, то берем из оригинала, если буква, то берем его из стека методом pop*/
    public static String getStackReverse(String rev){
        Stack<Character> s =  new Stack<>();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i< rev.length();i++){
            char c = rev.charAt(i);
            if(!Character.isDigit(c)){
                s.push(c);
            }
        }

        for(int i = 0; i<rev.length();i++){
            char c = rev.charAt(i);
            if(Character.isDigit(c)){
                res.append(c);
            }
            else{
                res.append(s.pop());
            }
        }
        return res.toString();
    }

    /*Deque является двухсторонним, имеет те же методы, что и стек, является боллее современным методом в использовании*/
    public static String getDequeReverse(String rev){
        ArrayDeque<Character> d = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        for(int i =0; i<rev.length(); i++){
            char c = rev.charAt(i);
            if(!Character.isDigit(c)){
                d.addFirst(c);
            }
        }
        for(int i = 0;i<rev.length();i++){
            char c = rev.charAt(i);
            if(Character.isDigit(c)){
                res.append(c);
            }
            else {
                res.append(d.pop());
            }
        }
        return res.toString();
    }

    /*Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 8
	at com.practical.Main.getIndexesReverse(Main.java:131)*/
    /*проходимся по массиву, в случае если число, то ставим на место,если нет, то проходимся по услови дальше, не являяется ли числом значение в индексе i, если да то пропускаем, если нет,
    то в обартном порядке рассавляем значения */
    public static String getIndexesReverse(String rev){
        char[] c = rev.toCharArray();
        char[] res = new char[c.length];

        int i = c.length -1;
        int j = 0;

        while(j<c.length){
            if(Character.isDigit(c[j])){
                res[j] = c[j];
                j++;
            }
            else {
                while (i >= 0 && Character.isDigit(c[i])) {
                    i--; // Пропускаем цифры в исходной строке с конца
                }
                if (i >= 0) {
                    res[j] = c[i];
                    i--;
                    j++;
                }
            }
        }
        return new String(res);
    }

    /*Если значение является цифрой, записываем его индекс и значение в список, если нет то записваем буквы в список w, далее в результирующий список собираем значения,
    создаем массив char результирующего размера, записываем в него цифры согласно индексу, далее проверяем пусто ли значение, если нет то записываем значения,
    если нет то записываем значения в обратном порядке*/
    public static String getArrayLReverse(String rev){
        List<Character>w = new ArrayList<>();
        List<Integer>nP = new ArrayList<>();
        List<Integer>nV = new ArrayList<>();

        for(int i = 0; i < rev.length(); i++) {
            char c = rev.charAt(i);

            if (Character.isDigit(c)) {
                nP.add(i);
                nV.add((int) c);
            } else {
                w.add(c);
            }
        }

        char[] res = new char[rev.length()];
        for(int i = 0;i<nP.toArray().length; i++){
                int pos = nP.get(i);
                int val = nV.get(i);
                res[pos] =(char)val;
        }

        int indx = w.size()-1;

        for(int i =0; i<w.size(); i++) {
            if (res[i] == 0) {
                res[i] = w.get(indx);
                indx--;
            }
        }
        return new String(res);
    }

}
