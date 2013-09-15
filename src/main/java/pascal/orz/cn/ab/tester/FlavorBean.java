/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pascal.orz.cn.ab.tester;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Getter;
import pascal.orz.cn.ab.tester.util.JsfUtil;

/**
 *
 * @author hnakada
 */
@Named
@SessionScoped
@Stateful
public class FlavorBean implements Serializable {

    @EJB
    private FlavorFacade flavorFacade;
    @Getter
    private Flavor current;

    public List<Flavor> getFlavors() {
        return flavorFacade.findAll();
    }

    public String $(String name) {
        return name + "?faces-redirect=true";
    }

    public String prepareShow(Long id) {
        this.current = flavorFacade.find(id);
        return $("show");
    }

    public String prepareList() {
        System.out.println("onList");
        return $("list");
    }

    public String update() {
        System.out.println("onUpdate");
        try {
            flavorFacade.edit(this.current);
            JsfUtil.addSuccessMessage("success update.");
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "failed update.");
            return null;
        }
    }

    public String create() {
        System.out.println("onCreating");
        try {
            flavorFacade.create(this.current);
            JsfUtil.addSuccessMessage("success update.");
            return "list";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, "failed update.");
            return null;
        }
    }

    public String prepareCreate() {
        System.out.println("onCreate");

        this.current = new Flavor();
        JsfUtil.addSuccessMessage("success update.");
        return "new";
    }

    public String getScripts() {
        List<Flavor> flavors = flavorFacade.findAll();
        int index = (int) (Math.random() * (flavors.size()));
        System.out.println("script-index:" + index);
        return flavors.get(index).getScritps();
    }
}
