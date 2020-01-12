import java.awt.*;
import java.util.Stack;

class StackList {
    private char Data[] = new char[10000];
    private int Top;

    //���췽��
    StackList() {
        Top = 0;
    }

    public boolean isFull(){
        if (Top >= 10000){

            //��һ���������������ʮ���ַ�����������ʾ
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

            //����Ѿ�ɾ���ɾ���Ҫɾ����������ʾ
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
