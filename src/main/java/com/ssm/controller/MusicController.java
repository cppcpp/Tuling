package com.ssm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alibaba.fastjson.JSON;
import com.ssm.entity.Music;
import com.ssm.service.MusicService;

/*@Controller
@RequestMapping(value="/music")*/
public class MusicController extends MultiActionController {
	/* @Autowired */
	private MusicService musicService;

	public void setMusicService(MusicService musicService) {
		this.musicService = musicService;
	}

	// 首次加载页面
	public void ajaxDatas(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute("page", null);
		request.getSession().setAttribute("pageSize", null);

		List<Music> lists = null;
		lists = getNowPageMusic(request);
		for (Music music : lists) {
			System.out.println(music.toString());
		}
		String jsonString = JSON.toJSONString(lists);
		renderData(response, jsonString);
	}

	// 下一页
	public void ajaxDatasNext(HttpServletRequest request, HttpServletResponse response) {
		List<Music> lists = null;
		lists = getNextPageMusic(request);
		for (Music music : lists) {
			System.out.println(music.toString());
		}
		String jsonString = JSON.toJSONString(lists);
		renderData(response, jsonString);
	}

	// 上一页
	public void ajaxDatasFront(HttpServletRequest request, HttpServletResponse response) {
		List<Music> lists = null;
		lists = getFrontPageMusic(request);
		for (Music music : lists) {
			System.out.println(music.toString());
		}
		String jsonString = JSON.toJSONString(lists);
		renderData(response, jsonString);
	}

	private List<Music> getFrontPageMusic(HttpServletRequest request) {
		List<Music> lists = getMusic(request, 0);
		return lists;
	}

	private List<Music> getNextPageMusic(HttpServletRequest request) {
		List<Music> lists = getMusic(request, 1);
		return lists;
	}
	
	private List<Music> getNowPageMusic(HttpServletRequest request){
		return getMusic(request, -1);
	}

	// 得到分页需要的音乐数据
	public List<Music> getMusic(HttpServletRequest request, int a) {
		List<Music> lists = new ArrayList<>();
		int totalMusic = musicService.getCount();
		System.out.println(musicService);
		System.out.println("总条数：" + totalMusic);

		int currentPage = (request.getSession().getAttribute("page") == null ? 1
				: (int) request.getSession().getAttribute("page"));

		int pageSize = request.getSession().getAttribute("pageSize") == null ? 6
				: (int) Integer.parseInt((String) request.getSession().getAttribute("pageSize"));

		// System.out.println("currentPage:"+currentPage+"
		// "+"pageSize:"+pageSize);

		// int/int 为int
		request.getSession().setAttribute("totalPage", (int) (Math.ceil((totalMusic + 0.0) / pageSize)));

		// 下一页
		if (a == 1)
			currentPage++;
		// 上一页
		if (a == 0)
			currentPage--;
		//当前页
		if(a==-1)
			;
		

		// 第一页
		if (currentPage <= 0) {
			currentPage = 1;
		}
		// 尾页
		if (currentPage > Math.ceil((totalMusic+0.0) / pageSize)) {
			currentPage = 1;
		}

		System.out.println("currentPage:" + currentPage + "    " + "pageSize:" + pageSize);
		int currentResult = (currentPage - 1) * pageSize;

		int getSize = (totalMusic - currentResult + 1) >= pageSize ? pageSize : totalMusic - currentResult + 1;
		System.out.println("currentResult:" + currentResult + "   " + "     " + (currentPage - 1) * pageSize
				+ "getSize:" + getSize);
		lists = musicService.getAllMusic(currentResult, getSize);
		System.out.println("lists 的大小：" + lists.size());

		request.getSession().setAttribute("page", currentPage);

		return lists;

	}

	// 输出json数据
	private void renderData(HttpServletResponse response, String data) {
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			response.setCharacterEncoding("utf-8");
			printWriter.print(data);
		} catch (IOException ex) {
			Logger.getLogger(MusicController.class.getName()).log(Level.ERROR, null, ex);
		} finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}

}
