package com.aiken.bibpaper.controller.login;

import javax.validation.Valid;

import com.aiken.bibpaper.domain.login.RegisterUser;
import com.aiken.bibpaper.domain.login.RegisterUserForm;
import com.aiken.bibpaper.service.login.RegisterUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/")
public class RegisterUserController {

    @Autowired
    private RegisterUserService registerUserService;

    /**
     * 会員情報入力画面に遷移する。
     */
    @GetMapping("RegistrationForm")
    public String showRegisterUserForm(Model model) {

        model.addAttribute(new RegisterUserForm());

        // 会員情報入力画面。
        return "RegistrationForm";
    }

    @PostMapping("Register")
    public String registerUserAccount(@Valid @ModelAttribute RegisterUserForm registerUserForm, BindingResult result)
            throws Exception {

        // USERテーブルにinsertする時の引数。
        RegisterUser entity = new RegisterUser();
        MultipartFile icon = registerUserForm.getImage();

        entity.setUserName(registerUserForm.getUserName());
        entity.setGraduate(registerUserForm.getGraduate());
        entity.setEmail(registerUserForm.getEmail());
        entity.setPassword(registerUserForm.getPassword());
        entity.setImage(icon.getOriginalFilename());

        if (!icon.isEmpty()) {
            try {
                // 保存先を定義
                String uploadPath = "src/main/resources/static/";
                byte[] bytes = icon.getBytes();

                // 指定ファイルへ読み込みファイルを書き込み
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(uploadPath + icon.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                /*
                 * // 圧縮 File input = new File(uploadPath + newFileName); BufferedImage image =
                 * ImageIO.read(input); OutputStream os = new FileOutputStream(input);
                 * Iterator<ImageWriter> writers = ImageIO .getImageWritersByFormatName("jpg");
                 * ImageWriter writer = (ImageWriter) writers.next(); ImageOutputStream ios =
                 * ImageIO.createImageOutputStream(os); writer.setOutput(ios); ImageWriteParam
                 * param = new JPEGImageWriteParam(null);
                 * param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                 * param.setCompressionQuality(0.30f); writer.write(null, new IIOImage(image,
                 * null, null), param); os.close(); ios.close(); writer.dispose();
                 */
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // USERテーブルにinsertする。
        registerUserService.registerUser(entity);

        return "Result";
    }
}