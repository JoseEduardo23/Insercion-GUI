import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Form1 {
    public JPanel MainPanel;
    public JTextField GNB2;
    public JTextField GNB1;
    public JTextField GCI;
    public JTextField GAPEL;
    public JTextField GNOMB;
    public JButton B1;
    public JLabel NB2;
    public JLabel NB1;
    public JLabel CI;
    public JLabel APEL;
    public JLabel NOMB;

    public Form1() {
        B1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String url = "jdbc:mysql://localhost:3306/estudiantes";
                String user = "root";
                String password = "j.eduardo23";

                String nombre = GNOMB.getText() + " " + GAPEL.getText();
                String cedula = GCI.getText();
                Double B1 = Double.parseDouble(GNB1.getText());
                Double B2 = Double.parseDouble(GNB2.getText());

                String sql = "INSERT INTO estudiantes (Cedula_EST, Nombre_EST, B1, B2) VALUES (?, ?, ?, ?)";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        PreparedStatement cadenaPreparada = connection.prepareStatement(sql);

                        cadenaPreparada.setString(1, cedula);
                        cadenaPreparada.setString(2, nombre);
                        cadenaPreparada.setDouble(3, B1);
                        cadenaPreparada.setDouble(4, B2);

                        int filasInsertadas = cadenaPreparada.executeUpdate();
                        if (filasInsertadas > 0) {
                            System.out.println("Se ha insertado correctamente el estudiante.");
                            JOptionPane.showMessageDialog(null, "DATOS INGRESADOS",null, JOptionPane.WARNING_MESSAGE);
                        } else {
                            System.out.println("No se ha podido insertar el estudiante.");
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}