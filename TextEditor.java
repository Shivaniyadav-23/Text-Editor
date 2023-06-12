import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor implements ActionListener{
    //Declaring properties of texreditor
    JFrame frame;
    
    JMenuBar menuBar;

    JMenu file,edit;

    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll,close;

    JTextArea textArea;

    TextEditor(){
        //Initialize  a frame
        frame = new JFrame();
        //Initialize a menubar
        menuBar = new JMenuBar();
        //text area initialize
         textArea = new JTextArea();
        // initialize a menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // initialize a file menu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");// all connect to aaction listner 
        
        //add  action listner  to file menu
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // initialize a edit menu items

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // add action listner to edit menu

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        // add to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //add menu items in file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //add menus to menuBar
        menuBar.add(file);
        menuBar.add(edit); 

        // set menubar to frame
        frame.setJMenuBar(menuBar);
        
        // before adding we create panel
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5   , 5, 5));
        panel.setLayout(new BorderLayout(0,0));
        
        // add textarea to panel
        panel.add(textArea, BorderLayout.CENTER);

        // crete scorll pane for scrolling
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        // add panel to frame
        frame.add(panel);

        // Set dimensions of frame
        frame.setBounds(100, 100, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            // perform cut operation
            textArea.cut();//inbuilt method in JTextArea
        }
       if(actionEvent.getSource()==copy){
            // perform copy operation similarly others
            textArea.copy();
        }

         if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
           //  open file chooser
           JFileChooser fileChooser = new JFileChooser("OS");
           int chooseOption = fileChooser.showOpenDialog(null);

           // if we clicked on open button

           if(chooseOption== JFileChooser.APPROVE_OPTION){
            // Getting file selected
            File file = fileChooser.getSelectedFile();

            // path of selected file
            String filePath = file.getPath();
            try{
                // initilize file reader
                FileReader fileReader = new FileReader(filePath);
            
                // initilize buffered reader
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String intermediate = "";
                String output = "";
               
                // read contents of file line by line
                while((intermediate=bufferedReader.readLine())!=null){
                    output+=intermediate +"\n";
                }

                // set the output string to text area
                textArea.setText(output);
                bufferedReader.close();
            } catch(IOException fileNotFoundException){
                fileNotFoundException.printStackTrace();
            }
           }
        }
        if(actionEvent.getSource()==saveFile){
            // initilize file picker
             JFileChooser fileChooser = new JFileChooser("OS");
             // get choose option from file chooser
             int chooseOption = fileChooser.showSaveDialog(null);
             //check if click on save button

             if(chooseOption==JFileChooser.APPROVE_OPTION){
                // create a new file with choosen directory path
                File file= new File(fileChooser.getSelectedFile().getAbsolutePath() +".txt");
                try{
                    // innilize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //intialize buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    // write content of text area in our new file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();

                }catch(IOException ioException){
                    ioException.printStackTrace();
                }
             }
        }
        if(actionEvent.getSource()==newFile){
           TextEditor newTextEditor = new TextEditor();
        }
        
    }
     public static void main(String[] args) {
         TextEditor textEditor = new TextEditor();
    }
}
