import java.awt.*;
import javax.swing.*;
import java.awt.print.PrinterException;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;


class editor extends JFrame implements ActionListener{
   JFrame f;


   JTextArea t;




//constractor will initialize the textarea and frame

editor(){
//    initializing the frame with title
    f=new JFrame("DeV!L");



//    setting the overAll theme of the Application
    try{
//        internal look ar jonne
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        MetalLookAndFeel.setCurrentTheme(new OceanTheme());
    }
    catch(Exception e){

    }

    t=new JTextArea();

//    creating the menubar
    JMenuBar menu=new JMenuBar();

//    creating menu file
    JMenu file=new JMenu("File");
//    creating menuItem for menu filw
    JMenuItem m1=new JMenuItem("New");
    JMenuItem m2=new JMenuItem("Open");
    JMenuItem m3=new JMenuItem("Save");
    JMenuItem m4=new JMenuItem("print");

    m1.addActionListener(this);
    m2.addActionListener(this);
    m3.addActionListener(this);
    m4.addActionListener(this);
//    item willl be added to the file menu;
    file.add(m1);
    file.add(m2);
    file.add(m3);
    file.add(m4);



//Now will creat the edit in the menubar as a menu
    JMenu edit=new JMenu("Edit");
    JMenuItem m5=new JMenuItem("Cut");
    JMenuItem m6=new JMenuItem("Copy");
    JMenuItem m7=new JMenuItem("Paste");

    m5.addActionListener(this);
    m6.addActionListener(this);
    m7.addActionListener(this);

    edit.add(m5);
    edit.add(m6);
    edit.add(m7);

//    Jmen
    JMenuItem close=new JMenuItem("Close");
    close.addActionListener(this);
//
    menu.add(file);
    menu.add(edit);
    menu.add(close);

//    now will add it ro the frame
    f.setJMenuBar(menu);
    f.add(t);
    f.show();

}




//here what button is clicked will contains oveer here in e
    public void actionPerformed(ActionEvent e){
//    function for adding the functionality for each of the button for the menu item
        String s=e.getActionCommand();
        if(s.equals("New")){
            t.setText("");
        }
        else  if(s.equals("Open")) {
            JFileChooser j = new JFileChooser("Desktop: ");

            int r = j.showOpenDialog(null);
//          clicked okay
//            File fi = null;
            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String s1 = "", s2 = "";
//                fr = null;
//                Fr contains all the data in the file
                    FileReader fr = new FileReader(fi);
//
//                Br contains the buffered dadta in the file
//                character by character
                    BufferedReader br = new BufferedReader(fr);

                    s2 = br.readLine();
                    while ((s1 = br.readLine()) != null) {
                        s2 = s2 + '\n' + s1;
                    }
                    t.setText(s2);
                } catch (Exception et) {
                    JOptionPane.showMessageDialog(f, et.getMessage());
                }
            }
        }


        else  if(s.equals("Save")){

            JFileChooser j =new JFileChooser("Desktop: ");

                int r=j.showSaveDialog(null);
                if(r==JFileChooser.APPROVE_OPTION){

                    File fi=new File(j.getSelectedFile().getAbsolutePath());
                    try{
                        FileWriter fw=new FileWriter(fi,false);
                        BufferedWriter bw=new BufferedWriter(fw);
                        bw.write(t.getText());
//                        after writing is finished we need to flush the stream
                        bw.flush();
                        bw.close();
                    }
                    catch(Exception et){
                    JOptionPane.showMessageDialog(f,et.getMessage());
                    }
                }


        }
        else  if(s.equals("print")){
            try {
                t.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        }
        else  if(s.equals("Copy")){
          t.copy();
        }
        else  if(s.equals("Paste")){
            t.paste();
        }
        else  if(s.equals("Cut")){
           t.cut();
        } else  if(s.equals("Close")){
            t.setVisible(false);
        }
//

    }






    public static void main(String[] args) {
        editor e=new editor();
    }


}
