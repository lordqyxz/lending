package com.p2p.lending.controller;

import com.p2p.lending.entity.Biao;
import com.p2p.lending.service.BiaoService;
import com.p2p.lending.util.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("biao")
public class BiaoController {
    static final String baseDir = "WEB-INF/view/";
    @Autowired
    private BiaoService biaoService;

    @RequestMapping("list")
    public String list(Model model, @RequestParam(value = "currpage", required = false) String currpage) {

        int pagerow = 5;// 每页5行
        int currpages = 1;// 当前页
        int totalpage = 0;// 总页数
        int totalrow = 0;// 总行数

        Biao biao = new Biao();

        List<Biao> list = biaoService.findList(BeanUtils.toMap(biao));
        totalrow = list.size();// 获取总行数
        if (currpage != null && !"".equals(currpage)) {
            currpages = Integer.parseInt(currpage);
        }
        totalpage = (totalrow + pagerow - 1) / pagerow;
        if (currpages < 1) {
            currpages = 1;
        }
        if (currpages > totalpage) {
            if (totalpage < 1) {
                totalpage = 1;
            }
            currpages = totalpage;
        }
        Integer startPage = (currpages - 1) * pagerow;
        biao.setStartPage(startPage);
        biao.setPageSize(5);

        List<Biao> list2 = biaoService.findList(BeanUtils.toMap(biao));

        model.addAttribute("list", list2);
        model.addAttribute("listNo", list);
        model.addAttribute("totalrow", totalrow);
        model.addAttribute("currpages", currpages);
        model.addAttribute("totalpage", totalpage);
        return baseDir + "bk_biao_list";
    }

    @RequestMapping(value = "save")
    public String save(Biao biao) {
        if (biao.getId() == null) {
            biaoService.create(biao);
        } else {
            biaoService.update(biao);
        }
        return "redirect:list.do";
    }

    @RequestMapping("input")
    public String input(Biao params, Model model) {
        Biao biao;
        if (params.getId() == null) {
            biao = new Biao();
        } else {
            biao = biaoService.get(params.getId());
        }
        model.addAttribute("domain", biao);
        return baseDir + "bk_input_biao";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam(value = "id", required = true) String bid) {
        biaoService.delete(Integer.parseInt(bid));
        return "redirect:list.do";
    }
}
