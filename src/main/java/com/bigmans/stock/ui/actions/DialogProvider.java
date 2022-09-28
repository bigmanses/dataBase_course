package com.bigmans.stock.ui.actions;

import com.bigmans.stock.db.*;
import com.bigmans.stock.ui.Act;
import com.bigmans.stock.ui.ActionId;
import com.bigmans.stock.ui.VersionType;
import com.bigmans.stock.ui.addition.CheckCorrect;
import com.bigmans.stock.ui.addition.ListenDialog;
import com.bigmans.stock.ui.addition.SaveUpdateData;

import javax.swing.*;


public class DialogProvider {
    /** Интерфейс сервисов для работы с БД */
    private Prototype service;
    /** Наше диалоговое окно */
    private JDialog dialog;
    private JFrame frame;
    private final JRadioButton postavka = new JRadioButton(VersionType.SUPPLY.getId());
    private final JRadioButton sale = new JRadioButton(VersionType.SALE.getId());
    private final JRadioButton shipped = new JRadioButton(VersionType.SHIPPED.getId());
    private final JRadioButton noShipped = new JRadioButton(VersionType.NO_SHIPPED.getId());
    private final JRadioButton pay = new JRadioButton(VersionType.PAYMENT.getId());
    private final JRadioButton noPay = new JRadioButton(VersionType.NO_PAYMENT.getId());

    ListenDialog listenDialog;
    public int createDialog(String key, JFrame frame, Prototype prototype, String act, JTable table, Integer numberStr) {
        this.service = prototype;
        this.frame = frame;
        this.dialog = new JDialog(frame, "add", true);
        if(act.equals(Act.ADD.getId())) {
            this.listenDialog = new ListenDialog( new SaveUpdateData(postavka, shipped, pay, frame, prototype, dialog), new CheckCorrect(prototype, dialog), frame);
            if (key.equals(ActionId.CLIENT.getId())) {
                JDialog dialog = CreatorDialog.getClientDialog(this.dialog);
                listenDialog.listenDialogClient(dialog);
                dialog.setVisible(true);
            }
            if(key.equals(ActionId.MANUFACTURE.getId())){
                JDialog dialog = CreatorDialog.getManufacturerDialog(this.dialog);
                listenDialog.listenDialogManufacturer(dialog);
                dialog.setVisible(true);
            }
            if(key.equals(ActionId.PRODUCT.getId())){
                JDialog dialog = CreatorDialog.getProductDialog(this.dialog);
                listenDialog.listenDialogProduct(dialog);
                dialog.setVisible(true);
            }
            if (key.equals(ActionId.CONTRACT.getId())) {
                JDialog dialog = CreatorDialog.getContractDialog(this.dialog, postavka, sale);
                listenDialog.listenDialogContract(dialog);
                dialog.setVisible(true);
            }
            if (key.equals(ActionId.SCORE.getId())) {
                JDialog dialog = CreatorDialog.getScoreDialog(this.dialog, shipped, noShipped, pay, noPay);
                listenDialog.listenDialogScore(dialog);
                dialog.setVisible(true);
            }
        } else if (act.equals(Act.DELETE.getId())){
            int answer = JOptionPane.showOptionDialog(frame, "Вы точно хотите удалить элемент?", "delete",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] {"Да", "Нет"}, "да");
        return answer;
        } else if (act.equals(Act.EDIT.getId())){
            this.listenDialog = new ListenDialog( new SaveUpdateData(postavka, shipped, pay, frame, prototype, dialog), new CheckCorrect(prototype, dialog), frame);
            if (key.equals(ActionId.CLIENT.getId())) {
                JDialog dialog = CreatorDialog.getClientDialog(this.dialog, EssenceProvider.getClient(table, numberStr));
                listenDialog.listenDialogClientUpdate(dialog, (int) table.getModel().getValueAt(numberStr, -1));
                dialog.setVisible(true);
                return 0;
            }
            if(key.equals(ActionId.MANUFACTURE.getId())){
                JDialog dialog = CreatorDialog.getManufacturerDialog(this.dialog, EssenceProvider.getManufacturer(table, numberStr));
                listenDialog.listenDialogManufacturerUpdate(dialog, (int) table.getModel().getValueAt(numberStr, -1));
                dialog.setVisible(true);
                return 0;
            }
            if(key.equals(ActionId.PRODUCT.getId())){
                JDialog dialog = CreatorDialog.getProductDialog(this.dialog, EssenceProvider.getProduct(table, numberStr));
                listenDialog.listenDialogProductUpdate(dialog, (int) table.getModel().getValueAt(numberStr, -1));
                dialog.setVisible(true);
                return 0;
            }
            if (key.equals(ActionId.CONTRACT.getId())) {
                JDialog dialog = CreatorDialog.getContractDialog(this.dialog, postavka, sale, EssenceProvider.getContract(table, numberStr));
                listenDialog.listenDialogContractUpdate(dialog, (int) table.getModel().getValueAt(numberStr, -1));
                dialog.setVisible(true);
                return 0;
            }
            if (key.equals(ActionId.SCORE.getId())) {
                JDialog dialog = CreatorDialog.getScoreDialog(this.dialog, shipped, noShipped, pay, noPay, EssenceProvider.getScore(table, numberStr));
                listenDialog.listenDialogScoreUpdate(dialog, (int) table.getModel().getValueAt(numberStr, -1));
                dialog.setVisible(true);
                return 0;
            }
        }
        return -1;
    }

    public int createDialog(JFrame frame, Prototype prototype, JTable table, Integer numberStr, String nameUser) {
        this.service = prototype;
        this.frame = frame;
        this.dialog = new JDialog(frame, "add", true);
        sale.setSelected(true);
        this.listenDialog = new ListenDialog( new SaveUpdateData(sale, shipped, pay, frame, prototype, dialog), new CheckCorrect(prototype, dialog), frame);
        JDialog dialog = CreatorDialog.getContractDialogClient(this.dialog, table, numberStr, nameUser);
        listenDialog.listenDialogContract(dialog);
        dialog.setVisible(true);
        return 0;
    }

}

