/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.other;

import java.sql.Timestamp;
import java.util.List;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.CommentDTO;
import tuanvxm.DTOs.UserDTO;

/**
 *
 * @author fightback
 */
public class Main {
    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(97, 10, 12, 14, 25, 17, 05);
        
//        List<Role> roleList = RoleList.ROLE_LIST;
//        for (Role r : roleList) {
//            System.out.print(r.getName() + "; ");
//        }
//        System.out.println("");
//        
//        List<Category> categoryList = CategoryList.CATEGORY_LIST;
//        for (Category c : categoryList) {
//            System.out.print(c.getName() + "; ");
//        }
//        System.out.println("");
//        
        UserDTO userDTO = new UserDTO("tuanvxm", "tuanvxm", "Vo Tuan", timestamp, 1, "QT", "0945664720",
                "tuanvxm@gmail.com", "197366906", 4, UserDTO.STATUS_AVAILABLE);
//        userDTO.createUser();
//
//        UserDTO userDTO2 = new UserDTO("tuanvxm", "tuanvxm2");
//        if (userDTO2.login()) {
//            System.out.println(userDTO2.getName());
//        } else {
//            System.out.println("Login failed");
//        }
//
//        userDTO.setName("Vo Xuan Minh Tuan");
//        userDTO.setUserID(11);
//        System.out.println(userDTO.updateUser());

//        userDTO = new UserDTO().findByUserName("reader1");
//        System.out.println(userDTO.getName());

//        System.out.println(new UserDTO().findByRoleID(4).size());

//        new UserDTO().changeStatus(10, UserDTO.STATUS_AVAILABLE);

//        System.out.println(new UserDTO().changePassword(10, "updated", "tested"));

//        System.out.println(new UserDTO().changeRoleID(10, 4, null));

//        ArticleDTO articleDTO = new ArticleDTO("testDTO", "testDTO", "testDTO", "", 4, 4, new Timestamp(0), 
//                ArticleDTO.STATUS_AVAILABLE);
//        
//        articleDTO.setArticleID(4);
//        articleDTO.setLastModifierID(4);
//        articleDTO.setLastModifiedTime(new Timestamp(1000));
//        
//        System.out.println(articleDTO.updateArticle());


//        ArticleDTO dto = new ArticleDTO(4, ArticleDTO.STATUS_HIDED, 10, new Timestamp(0));
//        System.out.println(dto.changeStatus());

//        System.out.println(new ArticleDTO().increaseViewCount(4));

//        System.out.println(new ArticleDTO().findByCreatorID(5).size());

//        System.out.println(new ArticleDTO().findByStatus("Hided").size());

//        System.out.println(new ArticleDTO().findByCategoryIDAndStatus(4, "Hided").get(0).getTitle());

//        System.out.println(new ArticleDTO().findTopViewCountCreatedAfterTime(2, new Timestamp(0)).get(1).getViewCount());

//        CommentDTO dto = new CommentDTO(4, 10, new Timestamp(0), "Moi tao", CommentDTO.STATUS_AVAILABLE);
//
//        System.out.println(dto.createComment());

//        CommentDTO dto = new CommentDTO(4, CommentDTO.STATUS_HIDE_BY_READER, 2, new Timestamp(0));
//        
//        System.out.println(dto.changeStatus());

        System.out.println(new CommentDTO().findByArticleID(1).size());
    }
}