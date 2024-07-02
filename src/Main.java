import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

public class Main {

    public static String HEADER =
            "ruler_designer_1560552532=\n" +
                    "{\n" +
                    "	type=?\n" +
                    "	id=0\n" +
                    "	genes=\n" +
                    "	{\n";

    public static String[] GENES = new String[]
            {
                    "hair_color",
                    "skin_color",
                    "eye_color",
                    "gene_chin_forward",
                    "gene_chin_height",
                    "gene_chin_width",
                    "gene_eye_angle",
                    "gene_eye_depth",
                    "gene_eye_height",
                    "gene_eye_distance",
                    "gene_eye_shut",
                    "gene_forehead_angle",
                    "gene_forehead_brow_height",
                    "gene_forehead_roundness",
                    "gene_forehead_width",
                    "gene_forehead_height",
                    "gene_head_height",
                    "gene_head_width",
                    "gene_head_profile",
                    "gene_head_top_height",
                    "gene_head_top_width",
                    "gene_jaw_angle",
                    "gene_jaw_forward",
                    "gene_jaw_height",
                    "gene_jaw_width",
                    "gene_mouth_corner_depth",
                    "gene_mouth_corner_height",
                    "gene_mouth_forward",
                    "gene_mouth_height",
                    "gene_mouth_width",
                    "gene_mouth_upper_lip_size",
                    "gene_mouth_lower_lip_size",
                    "gene_mouth_open",
                    "gene_neck_length",
                    "gene_neck_width",
                    "gene_bs_cheek_forward",
                    "gene_bs_cheek_height",
                    "gene_bs_cheek_width",
                    "gene_bs_ear_angle",
                    "gene_bs_ear_inner_shape",
                    "gene_bs_ear_bend",
                    "gene_bs_ear_outward",
                    "gene_bs_ear_size",
                    "gene_bs_eye_corner_depth",
                    "gene_bs_eye_fold_shape",
                    "gene_bs_eye_size",
                    "gene_bs_eye_upper_lid_size",
                    "gene_bs_forehead_brow_curve",
                    "gene_bs_forehead_brow_forward",
                    "gene_bs_forehead_brow_inner_height",
                    "gene_bs_forehead_brow_outer_height",
                    "gene_bs_forehead_brow_width",
                    "gene_bs_jaw_def",
                    "gene_bs_mouth_lower_lip_def",
                    "gene_bs_mouth_lower_lip_full",
                    "gene_bs_mouth_lower_lip_pad",
                    "gene_bs_mouth_lower_lip_width",
                    "gene_bs_mouth_philtrum_def",
                    "gene_bs_mouth_philtrum_shape",
                    "gene_bs_mouth_philtrum_width",
                    "gene_bs_mouth_upper_lip_def",
                    "gene_bs_mouth_upper_lip_full",
                    "gene_bs_mouth_upper_lip_profile",
                    "gene_bs_mouth_upper_lip_width",
                    "gene_bs_nose_forward",
                    "gene_bs_nose_height",
                    "gene_bs_nose_length",
                    "gene_bs_nose_nostril_height",
                    "gene_bs_nose_nostril_width",
                    "gene_bs_nose_profile",
                    "gene_bs_nose_ridge_angle",
                    "gene_bs_nose_ridge_width",
                    "gene_bs_nose_size",
                    "gene_bs_nose_tip_angle",
                    "gene_bs_nose_tip_forward",
                    "gene_bs_nose_tip_width",
                    "face_detail_cheek_def",
                    "face_detail_cheek_fat",
                    "face_detail_chin_cleft",
                    "face_detail_chin_def",
                    "face_detail_eye_lower_lid_def",
                    "face_detail_eye_socket",
                    "face_detail_nasolabial",
                    "face_detail_nose_ridge_def",
                    "face_detail_nose_tip_def",
                    "face_detail_temple_def",
                    "expression_brow_wrinkles",
                    "expression_eye_wrinkles",
                    "expression_forehead_wrinkles",
                    "expression_other",
                    "complexion",
                    "gene_height",
                    "gene_bs_body_type",
                    "gene_bs_body_shape",
                    "gene_bs_bust",
                    "gene_age",
                    "gene_eyebrows_shape",
                    "gene_eyebrows_fullness",
                    "gene_body_hair",
                    "gene_hair_type",
                    "gene_baldness",
                    "eye_accessory",
                    "teeth_accessory",
                    "eyelashes_accessory",
                    "hairstyles"
            };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ruler Designer DNA Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null); // Center the frame

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JLabel sexLabel = new JLabel("Select character sex:");
        panel.add(sexLabel);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setSelected(true);
        JRadioButton femaleButton = new JRadioButton("Female");

        ButtonGroup sexGroup = new ButtonGroup();
        sexGroup.add(maleButton);
        sexGroup.add(femaleButton);

        panel.add(maleButton);
        panel.add(femaleButton);

        JLabel codeLabel = new JLabel("Input character encrypted code:");
        panel.add(codeLabel);

        JTextField codeTextField = new JTextField();
        panel.add(codeTextField);

        JButton generateButton = new JButton("Generate DNA");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sex = maleButton.isSelected() ? "male" : "female";
                String encrypted = codeTextField.getText().trim();

                if (!encrypted.isEmpty()) {
                    try {
                        byte[] decodedBytes = Base64.getDecoder().decode(encrypted);
                        String designerDNA = generateDNA(sex, decodedBytes);
                        StringSelection stringSelection = new StringSelection(designerDNA);
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);

                        JOptionPane.showMessageDialog(frame, "Ruler Designer DNA has been copied to the clipboard!");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, "Invalid encrypted code.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter an encrypted code.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(generateButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static String generateDNA(String sex, byte[] decodedBytes) {
        int index = HEADER.indexOf("?");
        String designerDNA = HEADER.substring(0, index) + sex + HEADER.substring(index + 1);

        for (int i = 0, j = 0, l = GENES.length, k = decodedBytes.length; i < l && j < k; i++) {
            designerDNA += "        " + GENES[i] + "={ " + (decodedBytes[j++] & 0xFF) + " " + (decodedBytes[j++] & 0xFF) + " " + (decodedBytes[j++] & 0xFF) + " " + (decodedBytes[j++] & 0xFF) + " }\n";
        }

        designerDNA += "    }\n}";
        return designerDNA;
    }
}
