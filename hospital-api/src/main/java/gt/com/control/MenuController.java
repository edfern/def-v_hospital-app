package gt.com.control;

import gt.com.model.dto.ResponseMenuDto;
import gt.com.model.service.IMenuService;
import gt.com.model.service.MenuService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MenuController {

    @CrossOrigin
    @GetMapping(value = "/menu", produces = "application/json;charset=UTF-8")
    public ResponseMenuDto loginControl(HttpServletRequest request) throws Exception {
        IMenuService menuService = new MenuService();
        return  menuService.getMenus();
    }
}
