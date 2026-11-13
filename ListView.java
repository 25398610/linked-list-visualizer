import javax.swing.*;
import java.awt.*;

class Node{
    int val;
    Node next;

    Node (int val){
        this.val =val;
    }
}



class Board extends JPanel{
    Node head;
    int nextVal =1;

    Board() {
       push();
        push();
        push();
    }
    void push(){
        Node n =new Node(nextVal++);

        if(head ==null){
            head =n;
        }else{
            Node p =head;
            while(p.next!=null){
                p=p.next;
            }
            p.next=n;
        }
        repaint();
    }
    void pop(){
        if(head ==null)
            return;
        if(head.next==null){
            head=null;
        }else{
            Node p =head;
            while(p.next.next!=null){
                p=p.next;
            }
            p.next=null;
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int startX =80;
        int x =startX;
        int y =140;
        int w =110,h=50,gap=70;
        for(Node p =head;p!=null;p=p.next){
            if(x+w>getWidth()-20){
                x=startX;
                y+=h+40;
            }


            g.setColor(new Color(47,85,151));
            g.fillRect(x,y,w,h);

            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(p.val),x+w/2-6,y+h/2+6);
            int nextX =x+w+gap;
            if(p.next!=null&& nextX+w<= getWidth() -20){
                g.setColor(new Color(110,110,110));


                int cy =y+h/2;
                g.drawLine(x+w,cy,nextX,cy);
                g.fillPolygon(
                        new int[]{nextX,nextX-10,nextX-10},
                        new int []{cy,cy-6,cy+6}, //这些是调位置的 这里的是调出箭头这个图像的，上面的是调那个数字的位置的
                        3
                );
            }
            x+=w+gap;
        }





    }

    }





public class ListView  {
    public static void main(String[] args) {
        JFrame f =new JFrame("链表可视化");
        f.setSize(800,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board =new Board();

        JButton pushBtn =new JButton("push");
        JButton popBtn =new JButton("pop");

        pushBtn.addActionListener(e->board.push());
        popBtn.addActionListener(e->board.pop());

        JPanel bar =new JPanel();
        bar.add(pushBtn);
        bar.add(popBtn);

        f.add(bar,BorderLayout.NORTH);
        f.add(board,BorderLayout.CENTER);



        f.setVisible(true);
    }

}
