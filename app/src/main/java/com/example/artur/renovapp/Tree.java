package com.example.artur.renovapp;

import android.util.Log;

/**
 * Created by artur on 19/05/2017.
 * UNUFOR - Universidade de Fortaleza   CCT - Centro de Ciências Tecnológicas
 * Engenharia Eletrônica                Per:    2017.1
 * T923    Estrutura de Dados           Prof:   Gilson Pereira do Carmo Filho
 * Aluno:  Artur Alves Torres de Araujo Mat:    1610674
 */

class NodeTree {
    int     nodeID;
    String  questOrAns = null;
    NodeTree yesBranch  = null;
    NodeTree noBranch   = null;

    NodeTree(int newNodeID, String newQuestAns) {
        nodeID     = newNodeID;
        questOrAns = newQuestAns;
    }
}

class DecisionTree {

    ////////////////////////////////////////
    //CONSTANTES
    ////////////////////////////////////////
    private static final String TAG = "Decision Tree";
    ////////////////////////////////////////
    //ATRIBUTOS
    ////////////////////////////////////////
    //private static BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
    NodeTree rootNodeTree = null;
    NodeTree savedNodeTree;
    public String toDisplayInTextView;//testar/tentar usar essa variável pra exibir o valor do texto no display. Remover se n funcionar.

    ////////////////////////////////////////
    ///CONSTRUTORES
    ////////////////////////////////////////
    DecisionTree() {
    }

    ////////////////////////////////////////
    //PREENCHIMENTO DA ÁRVORE
    ////////////////////////////////////////
    public void createRoot(int newNodeID, String newQuestAns) {
        rootNodeTree = new NodeTree(newNodeID,newQuestAns);
        Log.e(TAG,"----Adding Root NodeTree------------------------------------------------------");
        Log.e(TAG,"Root NodeID: " + newNodeID);
        Log.e(TAG,"Root value: \"" + newQuestAns + "\"");
        Log.e(TAG,"----------------------------------------------Added Root NodeTree-------------");
    }

    public void addNode(int existingNodeID, int newNodeID, String newQuestAns, int key){
        if(key==1){
            addYesNode(existingNodeID, newNodeID, newQuestAns);
        }else if(key==0){
            addNoNode(existingNodeID, newNodeID, newQuestAns);
        }else {
            Log.e(TAG,"Erro");
        }
    }

    /* ADD YES NODE */
    private void addYesNode(int existingNodeID, int newNodeID, String newQuestAns) {
        // If no root node do nothing
        if (rootNodeTree == null) {
            Log.e(TAG,"ERROR: Árvore Vazia!");
            return;
        }
        // Search tree
        if (searchTreeAndAddYesNode(rootNodeTree,existingNodeID,newNodeID,newQuestAns)) {
            Log.e(TAG,"----------------------------------------------Added Yes NodeTree--------------");
        }
        else {
            Log.e(TAG,"NodeTree " + existingNodeID + " not found");
        }
    }
    private boolean searchTreeAndAddYesNode(NodeTree currentNodeTree, int existingNodeID, int newNodeID, String newQuestAns) {
        if (currentNodeTree.nodeID == existingNodeID) {//TRUE: Found node
            if (currentNodeTree.yesBranch == null) {
                currentNodeTree.yesBranch = new NodeTree(newNodeID,newQuestAns);
                Log.e(TAG,"----Adding YES NodeTree-------------------------------------------------------");
                Log.e(TAG,"Parent NodeID: " + existingNodeID + "\tParent Value: \"" + currentNodeTree.questOrAns + "\"");
                Log.e(TAG,"\t\tYES");
                Log.e(TAG,"Current NodeID: " + newNodeID + "\tCurrent value: " + newQuestAns);
            }
            else {
                Log.e(TAG,"WARNING: Overwriting previous node (id = " + currentNodeTree.yesBranch.nodeID + ") linked to yes branch of node " + existingNodeID);
                currentNodeTree.yesBranch = new NodeTree(newNodeID,newQuestAns);
            }
            return true;
        }else { // Try yes branch if it exists
            if (currentNodeTree.yesBranch != null) {
                if (searchTreeAndAddYesNode(currentNodeTree.yesBranch,existingNodeID,newNodeID,newQuestAns)) {
                    return true;
                }
                else { // Try no branch if it exists
                    if (currentNodeTree.noBranch != null) {
                        return(searchTreeAndAddYesNode(currentNodeTree.noBranch,existingNodeID,newNodeID,newQuestAns));
                    }
                    else {
                        return false;// Not found here
                    }
                }
            }
            return false;// Not found here
        }
    }

    /* ADD NO NODE */
    private void addNoNode(int existingNodeID, int newNodeID, String newQuestAns) {
        // If no root node do nothing
        if (rootNodeTree == null) {
            Log.e(TAG,"ERROR: No root node!");
            return;
        }
        // Search tree
        if (searchTreeAndAddNoNode(rootNodeTree,existingNodeID,newNodeID,newQuestAns)) {
            Log.e(TAG,"----------------------------------------------Added No NodeTree---------------");        }
        else {
            Log.e(TAG,"NodeTree " + existingNodeID + " not found");
        }
    }
    private boolean searchTreeAndAddNoNode(NodeTree currentNodeTree, int existingNodeID, int newNodeID, String newQuestAns) {
        if (currentNodeTree.nodeID == existingNodeID) {
            // Found node
            if (currentNodeTree.noBranch == null) {
                currentNodeTree.noBranch = new NodeTree(newNodeID,newQuestAns);
                Log.e(TAG,"----Adding NO NodeTree--------------------------------------------------------");
                Log.e(TAG,"Parent NodeID: " + existingNodeID + "\tParent Value: \"" + currentNodeTree.questOrAns + "\"");
                Log.e(TAG,"\t\tNO");
                Log.e(TAG,"Current NodeID: " + newNodeID + "\tCurrent value: " + newQuestAns);
            }
            else {
                Log.e(TAG,"WARNING: Overwriting previous node " + "id = " + currentNodeTree.noBranch.nodeID + ") linked to no branch of node " + existingNodeID);
                currentNodeTree.noBranch = new NodeTree(newNodeID,newQuestAns);
            }
            return(true);
        }
        else {
            // Try yes branch if it exists
            if (currentNodeTree.yesBranch != null) {
                if (searchTreeAndAddNoNode(currentNodeTree.yesBranch,existingNodeID,newNodeID,newQuestAns)) {
                    return(true);
                }
                else {
                    // Try no branch if it exists
                    if (currentNodeTree.noBranch != null) {
                        return(searchTreeAndAddNoNode(currentNodeTree.noBranch, existingNodeID,newNodeID,newQuestAns));
                    }
                    else return(false);	// Not found here
                }
            }
            else return(false);	// Not found here
        }
    }

    ////////////////////////////////////////
    //MÉTODOS DE BUSCA
    ////////////////////////////////////////
    public void queryBinTree() {
        queryBinTree(rootNodeTree);
    }
    private void queryBinTree(NodeTree currentNodeTree) {
        // Test for leaf node (answer) and missing branches
        if (currentNodeTree.yesBranch==null) {
            if (currentNodeTree.noBranch==null){
                savedNodeTree = currentNodeTree;
                toDisplayInTextView = currentNodeTree.questOrAns;
                Log.e(TAG, currentNodeTree.questOrAns);
                //TODO: Criar método para exibir o valor da linha acima na tela.
            }
            else {
                Log.e(TAG,"Error: Missing \"Yes\" branch at \"" + currentNodeTree.questOrAns + "\" question");
                //TODO: Criar método para exibir o valor da linha acima na tela.
                return;
            }
        }
        if (currentNodeTree.noBranch==null) {
            Log.e(TAG,"Error: Missing \"No\" branch at \"" + currentNodeTree.questOrAns + "\" question");
            //TODO: Criar método para exibir o valor da linha acima na tela.
            return;
        }
        askQuestion(currentNodeTree);
    }

    private void askQuestion(NodeTree currentNodeTree)  {
        Log.e(TAG,"Pergunta: " + currentNodeTree.questOrAns);
        savedNodeTree = currentNodeTree;
        toDisplayInTextView = currentNodeTree.questOrAns;
    }

    void resolver(String answer) {
        resolver(savedNodeTree,answer);
    }
    void resolver(NodeTree currentNodeTree, String answer) {
        if (answer.equals("Yes") && currentNodeTree.yesBranch!=null) {
            queryBinTree(currentNodeTree.yesBranch);
        }
        else {
            if (answer.equals("No") && currentNodeTree.noBranch!=null) {
                queryBinTree(currentNodeTree.noBranch);
            }
            else {
                Log.e(TAG,"ERROR: Must answer \"Yes\" or \"No\"");
                //TODO: Criar método para exibir o valor da linha acima na tela.
                askQuestion(currentNodeTree);
            }
        }
    }

    ////////////////////////////////////////
    /* OUTPUT BIN TREE */
    ////////////////////////////////////////
    public void outputBinTree() {
        outputBinTree("1", rootNodeTree);
    }
    private void outputBinTree(String tag, NodeTree currentNodeTree) {
        // Check for empty node
        if (currentNodeTree == null) return;
        // Output
        System.out.println("[" + tag + "] nodeID = " + currentNodeTree.nodeID + ", question/answer = " + currentNodeTree.questOrAns);
        // Go down yes branch
        outputBinTree(tag + ".1", currentNodeTree.yesBranch);
        // Go down no branch
        outputBinTree(tag + ".2", currentNodeTree.noBranch);
    }

    ////////////////////////////////////////
    //MÉTODOS DE PERCURSÃO
    ////////////////////////////////////////
    private void showPreOrder() {
        System.out.println("Exibindo árvore em pré-ordem");
        showPreOrder(rootNodeTree);
    }
    private void showPreOrder(NodeTree v){
        if (v!=null) {
            System.out.println("("+v.questOrAns+")");
            showPreOrder(v.yesBranch);
            showPreOrder(v.noBranch);
        }
    }
    private void showInOrder() {
        System.out.println("Exibindo árvore em ordem");
        showInOrder(rootNodeTree);
    }
    private void showInOrder(NodeTree v){
        if (v!=null) {
            showInOrder(v.yesBranch);
            System.out.println("("+v.questOrAns+")");
            showInOrder(v.noBranch);
        }
    }
    private void showPostOrder() {
        System.out.println("Exibindo árvore em pós-ordem");
        showPostOrder(rootNodeTree);
    }
    private void showPostOrder(NodeTree v) {
        if (v!=null) {
            showPostOrder(v.yesBranch);
            showPostOrder(v.noBranch);
            System.out.printf("(" + v.questOrAns + ")\t");
        }
    }
}