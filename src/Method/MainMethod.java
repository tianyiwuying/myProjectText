package Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class ThreadModth extends Thread{
    @Override
    public void run()
    {
        for (int i = 0; i < 100; i++ )
        {           
            System.out.println(i+"");
        }
    }
    
}
class MyCallable implements Callable<Object>
{
    private String taskNum;
    MyCallable(String taskNum)
    {
        this.taskNum=taskNum;
    }
    public Object call() throws Exception
    {
        System.out.println("��������"+taskNum);
        Date dateTemp=new Date();
        Thread.sleep(1000);
        Date dateTem=new Date();
        long time=dateTem.getTime()-dateTemp.getTime();
        System.out.println("������ֹ"+taskNum);
        return taskNum+"���񷵻����н������ǰ����ʱ��"+time+"����";
    }
}
@SuppressWarnings("unchecked")
public class MainMethod
{
    public static void main(String[] args) throws Exception
    {
      /*  System.out.println("123");
        Thread objThread=new ThreadModth();
        System.out.println(objThread.getName()); 
        Thread objThread1=new ThreadModth();
        System.out.println(objThread1.getName()); 
        objThread.start();
        objThread1.start();*/
        Date date1=new Date();
        int taskSize=5;
        //����һ���̳߳�
        ExecutorService pool=Executors.newFixedThreadPool(taskSize);
         List<Future> list=new ArrayList<Future>();
         for (int i = 0; i < taskSize; i++ )
        { 
            
            Callable<?> c=new MyCallable(i+"��Ȼ��");
            //ִ�����񲢻�ȡfuture����
            Future<?> objFuture=pool.submit(c);
            list.add(objFuture);
        }
         //�ر��̳߳�
         pool.shutdown();
        //��ȡ���в�����������н��
         for (Future<?> f:list)
         {
             //Future�����ϻ�ȡ����ķ���ֵ�������������̨
             System.out.println(">>>"+f.get().toString());
         }
         Date date2 = new Date();
         System.out.println("---�����������----����������ʱ��"+(date2.getTime()-date1.getTime()));
    }   
    
    
}


