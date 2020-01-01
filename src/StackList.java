import java.awt.*;
import java.util.Stack;

class StackList {
    private char Data[] = new char[41];
    private int Top;

    //构造方法
    StackList() {
        Top = 0;
    }

    public boolean isFull(){
        if (Top == 41){
            //若一个区块输入多于四十个字符，则声音提示
            Toolkit.getDefaultToolkit().beep();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmpty(){
        boolean b = false;
        if (Top <= 0){
            //如果已经删除干净还要删除，声音提示
            Toolkit.getDefaultToolkit().beep();
            b = true;
            return b;
        }
        else{
            return false;
        }
    }

    public boolean Pop(){
        if(!isEmpty())
        {
//            this.Top--;
            this.Data[--Top] = '\0';
        }
        return true;
    }

    public boolean Push(char e){
        if(!isFull())
        {
            this.Data[Top++] = e;
        }
        return true;
    }

    public StackList Clean(){
        StackList newList = new StackList();
        return newList;
    }
    public int length(){
        return Top;
    }
    public String getDatas(){
        return String.valueOf(Data);
    }

}
