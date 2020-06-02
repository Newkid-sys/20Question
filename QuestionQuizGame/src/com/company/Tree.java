package com.company;

//Use tree to represent the twenty question game

import java.io.*;

public class Tree {

    public TreeNode root; //Root of the tree

    public Tree() {
        root = new TreeNode();
    }

    public void loadTree(String filename){

        File file = new File(filename);
        file.mkdir();
        File newfile = new File(file, "QuizQuestions AND Answers");


        FileReader fileReader = null;
        BufferedReader buf = null;

        try{
            PrintWriter fileWriter = new PrintWriter(new FileWriter(newfile, true));
            fileReader = new FileReader(newfile);
            buf = new BufferedReader(fileReader);
            buildTree(root, buf);


        }catch(Exception e){
            System.out.println("Error during tree building "+e.getMessage());
        }finally{

            try {
                if (fileReader != null) {
                    fileReader.close();
                }

                if (buf != null) {
                    buf.close();
                }
            }catch(Exception e){
                System.out.println("Error during tree building "+e.getMessage());
            }

        }
    }

    //We build the three from a bufferedreader that uses a bread first strategy
    private void buildTree(TreeNode currentNode, BufferedReader buf) throws Exception{

        String line = buf.readLine();

        if(line != null){
            currentNode.setData(line);



            if(currentNode.isQuestion()){
                TreeNode yesNode = new TreeNode();
                TreeNode noNode = new TreeNode();
                currentNode.yes = yesNode;
                currentNode.no = noNode;
                buildTree(yesNode, buf);
                buildTree(noNode, buf);
            }
        }


    }
}
