import com.mysql.jdbc.Driver;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Seminee {
    private JPanel Main;
    private JTextField txtBrand;
    private JTextField txtCategorie;
    private JTextField txtPret;
    private JButton SAVEButton;
    private JTable table1;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEARCHButton;
    private JTextField txtID;
    private JScrollPane table_1;



    public static void main(String[] args) {
        JFrame frame = new JFrame("Seminee");
        frame.setContentPane(new Seminee().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    Connection con;
    PreparedStatement preparedStatement;

    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/semineedb","root","");
            System.out.println("Success");
        }catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }


    void table(){

        try
        {
            preparedStatement = con.prepareStatement("select * from seminee");
            ResultSet rs = preparedStatement.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }




    public Seminee() {
        connect();
        table();
    SAVEButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String brand,categorie,pret;

            brand=txtBrand.getText();
            categorie=txtCategorie.getText();
            pret=txtPret.getText();

            try {
                preparedStatement = con.prepareStatement("insert into seminee(brand,categorie,pret)values(?,?,?)");
                preparedStatement.setString(1, brand);
                preparedStatement.setString(2, categorie);
                preparedStatement.setString(3, pret);
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Ingregistrare adaugata");
                table();
                txtBrand.setText("");
                txtCategorie.setText("");
                txtPret.setText("");
                txtBrand.requestFocus();
            }

            catch (SQLException e1)
            {

                e1.printStackTrace();
            }






        }
    });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {

                    String semineeid = txtID.getText();

                    preparedStatement = con.prepareStatement("select brand,categorie,pret from seminee where id = ?");
                    preparedStatement.setString(1, semineeid);
                    ResultSet rs = preparedStatement.executeQuery();

                    if(rs.next()==true)
                    {
                        String semineu_brand = rs.getString(1);
                        String semineu_categorie = rs.getString(2);
                        String semineu_pret = rs.getString(3);

                        txtBrand.setText(semineu_brand);
                        txtCategorie.setText(semineu_categorie);
                        txtPret.setText(semineu_pret);

                    }
                    else
                    {
                        txtBrand.setText("");
                        txtCategorie.setText("");
                        txtPret.setText("");
                        JOptionPane.showMessageDialog(null,"ID invalid ");

                    }
                }
                catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String semineu_id,brand,categorie,pret;
                brand = txtBrand.getText();
                categorie = txtCategorie.getText();
                pret = txtPret.getText();
                semineu_id = txtID.getText();

                try {
                    preparedStatement = con.prepareStatement("update seminee set brand = ?,categorie = ?,pret = ? where id = ?");
                    preparedStatement.setString(1, brand);
                    preparedStatement.setString(2, categorie);
                    preparedStatement.setString(3, pret);
                    preparedStatement.setString(4, semineu_id);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Inregistrare actualizata cu succes!");
                    table();
                    txtBrand.setText("");
                    txtCategorie.setText("");
                    txtPret.setText("");
                    txtBrand.requestFocus();
                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }

        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String semineu_id;
                semineu_id = txtID.getText();

                try {
                    preparedStatement = con.prepareStatement("delete from seminee  where id = ?");

                    preparedStatement.setString(1, semineu_id);

                    preparedStatement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Stergerea a fost realizata cu succes!");
                    table();
                    txtBrand.setText("");
                    txtCategorie.setText("");
                    txtPret.setText("");
                    txtBrand.requestFocus();
                }

                catch (SQLException e1)
                {

                    e1.printStackTrace();
                }
            }

        });
    }
}
