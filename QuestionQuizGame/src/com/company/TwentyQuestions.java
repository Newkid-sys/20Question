package com.company;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TwentyQuestions {

    private Tree tree;
    private TreeNode currentNode;
    private JButton yesButton, noButton, restartButton;//Used to display the buttons
    private JTextPane textpane;//Used to display the question
    private boolean started = false;

    private ActionListener btnsListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();

            if(source == yesButton)
                yes();
            else if(source == noButton)
                no();
            else if(source == restartButton)
                restart();

        }
    };

    public static void main(String[] args) throws IOException {

        TwentyQuestions twentyQuestions = new TwentyQuestions();
        twentyQuestions.tree = new Tree();
        twentyQuestions.tree.loadTree("Questions");

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                twentyQuestions.buildUI();
            }
        });

    }

    public void buildUI(){
        //Build a UI or User Interface

        JFrame jFrame = new JFrame("20 Questions on ahmedmayan");
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container contentPane = jFrame.getContentPane();


        //Add buttons
        JPanel buttonsPanel = new JPanel();
        yesButton = new JButton("Yes");
        yesButton.addActionListener(btnsListener);
        buttonsPanel.add(yesButton, BorderLayout.LINE_START);

        noButton = new JButton("No");
        noButton.addActionListener(btnsListener);
        buttonsPanel.add(noButton, BorderLayout.LINE_END);

        restartButton = new JButton("Start");
        restartButton.addActionListener(btnsListener);
        buttonsPanel.add(restartButton, BorderLayout.LINE_START);

        contentPane.add(buttonsPanel, BorderLayout.PAGE_END);

        //Add a text area

        textpane = new JTextPane();
        textpane.setEditable(false);
        updateText("Think of a animal. Then press start");


        //We define some style for the text pane
        ///Users/ahmedmayan/Documents/QuestionQuizGame/questions.txt
        SimpleAttributeSet bSet = new SimpleAttributeSet();
        StyleConstants.setAlignment(bSet, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(bSet, 22);
        StyledDocument doc = textpane.getStyledDocument();
        doc.setParagraphAttributes(0, doc.getLength(), bSet, false);

        contentPane.add(textpane, BorderLayout.CENTER);

        jFrame.setMinimumSize(new Dimension(500, 250));

        //Used to center the JFrame
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int coordinatesX = (objDimension.width - jFrame.getWidth()) / 2;
        int coordinatesY = (objDimension.height - jFrame.getHeight()) / 2;
        jFrame.setLocation(coordinatesX, coordinatesY);

        //Used to display the window
        jFrame.pack();
        jFrame.setVisible(true);




    }

    //We need to write some code for the callback methods such as yes, no, and restart
    private void yes(){
        //We navigate in the tree by moving the current node on the yes branch
        if(started && currentNode != null){
            currentNode = currentNode.yes;

            if(currentNode != null){
                if(currentNode.isQuestion()){
                    updateText(currentNode.data);
                }
            }else {
                System.out.println("Would you like to "+currentNode.data+"?");
            }
        }else {
            updateText("I found : )");

        }

    }

    private void no(){
        //We navigate in the tree by moving the current node on the no branch
        if(started && currentNode != null){
            currentNode = currentNode.no;

            if(currentNode != null){
                if(currentNode.isQuestion()){
                    updateText(currentNode.data);
                }
            }else {
                System.out.println("Would you like to "+currentNode.data+" ?");
            }
        }else{
            updateText("I lost! :( ");
        }
    }

    private void restart(){
        if(started){
            started = false;
            updateText("Think of an animal, then click on start");
        }else{
            started = true;
            updateText("Think of an animal, then click on start");
            currentNode = tree.root;
            updateText(currentNode.data);
        }
    }

    private void updateText(String txt){
        textpane.setText("\n"+txt);
    }
}
