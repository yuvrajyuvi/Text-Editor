import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu File,Edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor(){
        //Initializing the frame
        frame=new JFrame();
        //Initializing the menuBar and menu
        menuBar=new JMenuBar();
        File=new JMenu("File");
        Edit=new JMenu("Edit");

        menuBar.add(File);
        menuBar.add(Edit);

        //Initializing the File menuItems
        newFile=new JMenuItem("New File");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");

        File.add(newFile);
        File.add(openFile);
        File.add(saveFile);

        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);


        //Initializing the Edit menuItems
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(selectAll);
        Edit.add(close);


        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //Initializing the textArea
        textArea=new JTextArea();
        frame.add(textArea);
        //Initializing the scroll bar
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel=new JPanel();

        panel.setBorder(new EmptyBorder(8,8,8,8));
        panel.setLayout(new BorderLayout(0,0));

        panel.add(scrollPane);
        frame.add(panel);
        frame.setJMenuBar(menuBar);
        frame.setTitle("Text Editor");
        frame.setBounds(100,100,400,400);
        frame.setVisible(true);
    }
    public static void main(String[]args) {
        TextEditor textEditor = new TextEditor();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==newFile){
            TextEditor newTextEditor=new TextEditor();
        }
        if(e.getSource()==saveFile){
            JFileChooser fileChooser=new JFileChooser("c:");

            fileChooser.setApproveButtonText("Save");
            int chooserOption =fileChooser.showSaveDialog(null);
            if(chooserOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                String filePath= file.getPath();
                try{
                    BufferedWriter outFile=null;
                    outFile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outFile);
                    outFile.close();
                }
                catch(Exception exception){
                    System.out.println("exception");
                }
            }
        }
        if(e.getSource()==openFile){
            JFileChooser fileChooser= new JFileChooser("c:");
            int choosePath=fileChooser.showOpenDialog(null);
            if(choosePath==JFileChooser.APPROVE_OPTION){
            File file =fileChooser.getSelectedFile();
            String filePath=file.getPath();

            try{
                BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                String Intermediate="",output="";
                while ((Intermediate =bufferedReader.readLine())!=null){
                    output+= Intermediate + "\n";
                }
                textArea.setText(output);
            }
            catch (Exception exception){
                System.out.println(exception);
            }
            }
        }
        if(e.getSource()==cut){
            textArea.cut();
        }
        if(e.getSource()==copy){
            textArea.copy();
        }
        if(e.getSource()==paste){
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            textArea.selectAll();
        }
        if(e.getSource()==close) {
            System.exit(0);
        }
    }
}