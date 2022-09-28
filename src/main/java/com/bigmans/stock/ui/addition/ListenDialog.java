package com.bigmans.stock.ui.addition;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListenDialog {
    SaveUpdateData saveUpdateData;
    CheckCorrect checkCorrectInput;
    JFrame frame;

    public ListenDialog(SaveUpdateData saveUpdateData, CheckCorrect checkCorrectInput, JFrame frame) {
        this.saveUpdateData = saveUpdateData;
        this.checkCorrectInput = checkCorrectInput;
        this.frame = frame;
    }

    public void listenDialogClient(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            if(!checkCorrectInput.checkCorrect(texts)){
                saveUpdateData.saveClient(texts);
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogManufacturer(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                saveUpdateData.saveManufacturer(texts);
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogProduct(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                if(checkCorrectInput.checkManufacturer(texts.get(texts.size()-1)) != -1) {
                    saveUpdateData.saveProduct(texts, checkCorrectInput.checkManufacturer(texts.get(texts.size()-1)));
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого производителя нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    /**
     * Действия, выполняющие при нажатие книпки
     * */
    public void listenDialogContract(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();

            if(!checkCorrectInput.checkCorrect(texts)){
                if(checkCorrectInput.checkContract(texts.get(3), texts.get(6)).size() > 1) {
                    if(checkCorrectInput.checkCorrectAmount(texts.get(4),checkCorrectInput.checkContract(texts.get(3), texts.get(6)).get(0))) {
                        saveUpdateData.saveContract(texts, checkCorrectInput.checkContract(texts.get(3), texts.get(6)));
                    }
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого клиента/продукта нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogScore(JDialog dialog){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                if(checkCorrectInput.checkScore(texts.get(2)) != -1) {
                    saveUpdateData.saveScore(texts,checkCorrectInput.checkScore(texts.get(2)));
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого контракта нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogClientUpdate(JDialog dialog, int id){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            if(!checkCorrectInput.checkCorrect(texts)){
                saveUpdateData.updateClient(texts, id);
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogManufacturerUpdate(JDialog dialog, int id){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                saveUpdateData.updateManufacturer(texts, id);
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogProductUpdate(JDialog dialog, int id){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            java.util.List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                if(checkCorrectInput.checkManufacturer(texts.get(texts.size()-1)) != -1) {
                    saveUpdateData.updateProduct(texts, checkCorrectInput.checkManufacturer(texts.get(texts.size()-1)), id);
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого производителя нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogContractUpdate(JDialog dialog, int id){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                if(checkCorrectInput.checkContract(texts.get(3), texts.get(6)).size() > 1) {
                    saveUpdateData.updateContract(texts,checkCorrectInput.checkContract(texts.get(3), texts.get(6)), id);
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого клиента/продукта нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }

    public void listenDialogScoreUpdate(JDialog dialog, int id){
        Component[] components = dialog.getContentPane().getComponents();
        Button button = (Button) dialog.getContentPane().getComponents()[components.length - 1];
        button.addActionListener(actionEvent1 -> {
            List<JTextField> texts = new ArrayList<>();
            checkCorrectInput.checkCorrect(texts);
            if(!checkCorrectInput.checkCorrect(texts)){
                if(checkCorrectInput.checkScore(texts.get(2)) != -1) {
                    saveUpdateData.updateScore(texts,checkCorrectInput.checkScore(texts.get(2)), id);
                } else{
                    dialog.setVisible(false);
                    JOptionPane.showMessageDialog(frame, "Такого контракта нет в Базе", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dialog.setVisible(false);
            }
        });
    }
}
