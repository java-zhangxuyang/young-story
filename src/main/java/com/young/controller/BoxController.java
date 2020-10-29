package com.young.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.young.base.constant.Const;
import com.young.base.support.ResponseBo;
import com.young.model.Box;
import com.young.model.BoxNote;
import com.young.model.BoxSubscribeNote;
import com.young.model.PassengerFlowNote;
import com.young.model.Staff;
import com.young.service.BoxService;
import com.young.service.DictService;
import com.young.service.PassFlowService;

import jodd.datetime.JDateTime;
import jodd.util.StringUtil;

@Controller
@RequestMapping("/admin/box")
public class BoxController {
	
	@Autowired
	private BoxService boxService;
	
	@Autowired
	private PassFlowService passFlowService;
	
	/**
	 * 后端包厢管理主页
	 */
	@GetMapping("/boxIndex")
	public String boxIndex(Integer pageNum,Model model,HttpServletRequest request) {
		List<Box> list = boxService.getBoxList();
		Map<String, List<BoxSubscribeNote>> noteMap = boxService.selectSubscribeToday();
		List<Box> xiaobao = new ArrayList<Box>();
		List<Box> dabao = new ArrayList<Box>();
		List<Box> langrensha = new ArrayList<Box>();
		List<Box> jubensha = new ArrayList<Box>();
		for (Box box : list) {
			if(Const.BOX_USE_STATUS == box.getStatus()) {
				box = this.boxSupTime(box);
			}
			if(noteMap.containsKey(box.getId().toString())) {
				box.setNoteList(noteMap.get(box.getId().toString()));
			}
			if(Const.BOX_TYPE_XIAOBAO == box.getType()) {
				xiaobao.add(box);
			}else if(Const.BOX_TYPE_DABAO == box.getType()) {
				dabao.add(box);
			}else if(Const.BOX_TYPE_LANGRENSHA == box.getType()) {
				langrensha.add(box);
			}else if(Const.BOX_TYPE_JUBENSHA == box.getType()) {
				jubensha.add(box);
			}
		}
		PageInfo<PassengerFlowNote> passList = passFlowService.selectPassFlowToday(pageNum);
		model.addAttribute("passFlows", passList);
		model.addAttribute("xiaobao", xiaobao);
		model.addAttribute("dabao", dabao);
		model.addAttribute("langrensha", langrensha);
		model.addAttribute("jubensha", jubensha);
		return "/admin_box";
	}
	@GetMapping("/useBoxForm")
	public String useBoxForm(Model model,HttpServletRequest request,Integer id) {
		model.addAttribute("id", id);
		return "/page/useBoxForm";
	}
	/*
	 * 包厢剩余时间（秒）
	 */
	public Box boxSupTime(Box box) {
		if(Const.BOX_USE_STATUS == box.getStatus()) {
			JDateTime now = new JDateTime();
			if(box.getRemind() == 1) {
				JDateTime toTime = new JDateTime(box.getToTime());
				box.setSupTime((toTime.getTimeInMillis()-now.getTimeInMillis())/1000);
			}else {
				JDateTime admissionTime = new JDateTime(box.getAdmissionTime());
				box.setSupTime((now.getTimeInMillis() - admissionTime.getTimeInMillis())/1000);
			}
		}
		return box;
	}
	
	
	/*
	 * 文字转语音并朗读
	 */
	@GetMapping("/boxRemind")
	public void boxRemind(String text) {
		 // ？？ 这个Sapi.SpVoice是需要安装什么东西吗，感觉平白无故就来了
	    ActiveXComponent sap = new ActiveXComponent("Sapi.SpVoice");
	    // Dispatch是做什么的？
	    Dispatch sapo = sap.getObject();
	    try {

	        // 音量 0-100
	        sap.setProperty("Volume", new Variant(100));
	        // 语音朗读速度 -10 到 +10
	        sap.setProperty("Rate", new Variant(-2));

	        Variant defalutVoice = sap.getProperty("Voice");

	        Dispatch dispdefaultVoice = defalutVoice.toDispatch();
	        Variant allVoices = Dispatch.call(sapo, "GetVoices");
	        Dispatch dispVoices = allVoices.toDispatch();
	        Dispatch.put(sapo, "Volume", new Variant(100));

	        Dispatch setvoice = Dispatch.call(dispVoices, "Item", new Variant(1)).toDispatch();
	        ActiveXComponent voiceActivex = new ActiveXComponent(dispdefaultVoice);
	        ActiveXComponent setvoiceActivex = new ActiveXComponent(setvoice);

	        Variant item = Dispatch.call(setvoiceActivex, "GetDescription");
	        // 执行朗读
	        Dispatch.call(sapo, "Speak", new Variant(text));

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        sapo.safeRelease();
	        sap.safeRelease();
	    }
	}
	
	//包厢使用
	@ResponseBody
	@PostMapping("/useBox")
	private Object useBox(BoxNote boxNote) {
		if(boxNote.getNumber() <= 0) {
			return ResponseBo.fail("请输入正确的号码牌！");
		}
		if(boxNote.getUseDate() <= 0 ) {
			return ResponseBo.fail("请输入正确的用时！");
		}
		if(boxNote.getPeople() <= 0) {
			return ResponseBo.fail("请输入正确的人数！");
		}
		@SuppressWarnings("rawtypes")
		ResponseBo data = boxService.useBox(boxNote);
		return data;
	}
	
	//修改包厢名字
	@ResponseBody
	@PostMapping("/updateBoxName")
	private Object updateBoxName(Box box) {
		if(StringUtil.isBlank(box.getName())) {
			return ResponseBo.fail("名字不能为空！");
		}
		int i = boxService.updateBoxName(box);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	//续时
	@ResponseBody
	@PostMapping("/continuation")
	private Object continuation(Box box) {
		if(box.getUseDuration() <= 0) {
			return ResponseBo.fail("请输入正确的数量！");
		}
		int i = boxService.continuation(box);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	//出包
	@ResponseBody
	@PostMapping("/departureBox")
	private Object departureBox(Box box) {
		int i = boxService.departureBox(box);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	//预约包厢
	@ResponseBody
	@PostMapping("/makeBox")
	private Object makeBox(BoxSubscribeNote boxSubscribeNote) {
		int i = boxService.makeBox(boxSubscribeNote);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	
	//更新预约包厢状态
	@ResponseBody
	@PostMapping("/updaetMakeBox")
	private Object updaetMakeBox(BoxSubscribeNote boxSubscribeNote) {
		int i = boxService.updaetMakeBox(boxSubscribeNote);
		if(i > 0) {
			return ResponseBo.ok();
		}else {
			return ResponseBo.fail();
		}
	}
	

}
