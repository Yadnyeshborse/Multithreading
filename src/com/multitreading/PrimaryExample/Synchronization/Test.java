package com.multitreading.PrimaryExample.Synchronization;

public class Test {
    public static void main(String[] args) {
        Counter counter=new Counter();
        Testo testo1 =new Testo(counter);
        Testo testo2=new Testo(counter);
        testo1.start();
        testo2.start();
        try {
            testo1.join();
            testo2.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(counter.getCount());
        //it need to return value as 2000 but it dies not return it due to absence of synchronized block
        //testo1 and testo2 are executing parallel but there is no perfect sequence as absence of synchronized block
        //both test1 and test2 will read value at same time like 101 101 so they will skip value in rder to avoid this we need synchronized block
        //
    }

}
