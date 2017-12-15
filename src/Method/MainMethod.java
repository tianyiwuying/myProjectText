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
        System.out.println("任务启动"+taskNum);
        Date dateTemp=new Date();
        Thread.sleep(1000);
        Date dateTem=new Date();
        long time=dateTem.getTime()-dateTemp.getTime();
        System.out.println("任务终止"+taskNum);
        return taskNum+"任务返回运行结果，当前任务时间"+time+"毫秒";
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
        //创建一个线程池
        ExecutorService pool=Executors.newFixedThreadPool(taskSize);
         List<Future> list=new ArrayList<Future>();
         for (int i = 0; i < taskSize; i++ )
        { 
            
            Callable<?> c=new MyCallable(i+"自然美");
            //执行任务并获取future对象
            Future<?> objFuture=pool.submit(c);
            list.add(objFuture);
        }
         //关闭线程池
         pool.shutdown();
        //获取所有并发任务的运行结果
         for (Future<?> f:list)
         {
             //Future对象上获取任务的返回值，并输出到控制台
             System.out.println(">>>"+f.get().toString());
         }
         Date date2 = new Date();
         System.out.println("---程序结束运行----，程序运行时间"+(date2.getTime()-date1.getTime()));
    }   
    
    
}


