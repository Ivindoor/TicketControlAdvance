package com.sanmina.tk.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanmina.tk.orm.dao.ContainerDao;
import com.sanmina.tk.orm.dao.ProjectDao;
import com.sanmina.tk.orm.dao.TicketDao;
import com.sanmina.tk.orm.dao.UserDao;
import com.sanmina.tk.orm.dao.ZPLDao;
import com.sanmina.tk.orm.dao.tbl_TicketDeletedDao;
import com.sanmina.tk.orm.model.Container;
import com.sanmina.tk.orm.model.Project;
import com.sanmina.tk.orm.model.Ticket;
import com.sanmina.tk.orm.model.Users;
import com.sanmina.tk.orm.model.tblContainerJSON;
import com.sanmina.tk.orm.model.tbl_TicketDeleted;
import com.sanmina.tk.zpl.PrintZPL;

@Controller
public class RequestDispatcher {

	@Autowired
	private tbl_TicketDeletedDao tbl_ticketDeletedDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private ContainerDao containerDao;
	@Autowired
	public ZPLDao zpldao;
	private List<Container> containerList = new ArrayList<Container>();
	private List<Container> container;
	private String revision = "";
	private String serial = "";
	private String partNumber = "";
	private String qtyDigital = "";

	@RequestMapping(value = "/")
	public ModelAndView landingResolv(ModelMap model, HttpSession session, Principal principal) {
		try {
			Users currentUser = (Users) session.getAttribute("userCredential");
			if (currentUser == null) {
				currentUser = userDao.findUserByUserLogin(principal.getName());
				model.addAttribute("currentUser", currentUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("index", model);
	}

	@RequestMapping(value = "levelTicket")
	public @ResponseBody String levelTicket(Principal principal) {
		String levelTicket = "";
		Users validate = userDao.findUserByUserLogin(principal.getName());
		if (validate.getLevelTicket() != null) 
			levelTicket = "True";
		else
			levelTicket="False";
		return levelTicket;
	}
	
	@RequestMapping(value = "levelSkid")
	public @ResponseBody String levellevelSkidTicket(Principal principal) {
		String levelSkid = "";
		Users validate = userDao.findUserByUserLogin(principal.getName());
		if (validate.getLevelSkid() != null) 
			levelSkid = "True";
		else
			levelSkid="False";
		return levelSkid;
	}

	@RequestMapping(value = "home")
	public ModelAndView landingResolv(ModelMap model) {
		return new ModelAndView("home/home", model);
	}

	@RequestMapping(value = "HojaSkid")
	public ModelAndView landingResolvin(ModelMap model) {
		return new ModelAndView("skid/HojaSkid", model);
	}

	@RequestMapping(value = "/login")
	public String loginResolv(ModelMap model, HttpServletRequest request) {
		model.addAttribute("userCredential", new Users());
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null)
				new SecurityContextLogoutHandler().logout(request, response, auth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "BuscarTicket")
	public ModelAndView SearchTicket(ModelMap model) {
		model.addAttribute("a", 5);
		return new ModelAndView("BuscarTicket/BuscarTicket", model);
	}

	@RequestMapping(value = "searchLevelUser", method = RequestMethod.POST)
	public @ResponseBody Integer SearchTicket(Integer lvlTicket, HttpSession session, Principal principal) {
		try {
			Users currentUser = (Users) session.getAttribute("userCredential");
			if (currentUser == null)
				currentUser = userDao.findUserByUserLogin(principal.getName());
			lvlTicket = currentUser.getLevelTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lvlTicket;
	}

	@RequestMapping(value = "deleteTicket", method = RequestMethod.POST)
	public @ResponseBody Boolean DeleteTicket(@RequestParam("ticke_or_contrainer") String ticke_or_contrainer,
			@RequestParam("comment") String comment, HttpServletRequest request) {
		boolean status = false;
		try {
			Ticket ticketDeleted = new Ticket();
			tbl_TicketDeleted tbl_ticketDeleted = new tbl_TicketDeleted();
			if (ticketDao.findTicketByTicket(ticke_or_contrainer) != null) {
				ticketDeleted = ticketDao.findTicketByTicket(ticke_or_contrainer);
				tbl_ticketDeleted.setId_ticket(ticketDeleted.getIdTicket());
				tbl_ticketDeleted.setIdProject(ticketDeleted.getProjectId());
				tbl_ticketDeleted.setTicket(ticketDeleted.getTicket());
				tbl_ticketDeleted.setDescription(comment);
				tbl_ticketDeleted.setDteDeleted(new Date());
				tbl_ticketDeleted.setUserName(request.getUserPrincipal().getName());
				tbl_ticketDeletedDao.saveTbl_TicketDeleted(tbl_ticketDeleted);
				ticketDao.deleteTicketByTicket(ticketDeleted);
				status = true;
			} else if (ticketDao.findTicketByContainer(ticke_or_contrainer) != null) {
				ticketDeleted = ticketDao.findTicketByContainer(ticke_or_contrainer);
				ticketDao.deleteTicketByTicket(ticketDeleted);
				status = true;
			} else
				status = false;
		} catch (Exception e) {
			status = false;
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "ticketAvailable", method = RequestMethod.POST)
	public @ResponseBody String NewTicket(ModelMap model, @RequestParam("select") String nameProject) {
		Ticket ticketAvailable = new Ticket();
		try {
			ticketAvailable = ticketDao.findTicketAvailable();
			ticketAvailable.setEnabled("1");
			ticketDao.updateTicket(ticketAvailable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketAvailable.getTicket();
	}

	@RequestMapping(value = "sizeNokia", method = RequestMethod.POST)
	public @ResponseBody String SizeNokia() {
		String status = "";
		try {
			System.out.println("Size Container List: " + containerList.size());
			if (containerList.size() > 5)
				status = "Excedio el limite";
			else
				status = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "validatePartNumber", method = RequestMethod.POST)
	public @ResponseBody String ValidatePartNumber(String contenedor, String proyecto) {
		String status = "";
		try {
			String newSerial = "";
			if (proyecto.equals("NOKIA") && contenedor.substring(0, 3).equals("25S"))
				newSerial = contenedor.substring(3, contenedor.length());
			else
				newSerial = contenedor;
			serial = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
					+ newSerial.replace(" ", "%20");
			JSONObject jsonSerial = jsonView(serial);
			JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
			JSONObject jsonLocation = (JSONObject) jsonSerial.get("location");
			if (jsonLocation.getString("shortWorkStation").equals("COMP")) {
				if (partNumber == "")
					partNumber = jsonPart.getString("partNumber");
				if (!jsonPart.getString("partNumber").equals(partNumber))
					status = "Numero de parte distinto";
				else
					status = "Success";
			} else
				status = "El serial no se encuntra en COMP";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "addContainer", method = RequestMethod.POST)
	public @ResponseBody ModelAndView AddContainer(ModelMap model, @RequestParam("contenedor") String contenedor,
			@RequestParam("proyecto") String proyecto) throws IOException, JSONException, ParseException {
		try {
			Container container = new Container();
			String newSerial = "";
			if (proyecto.equals("NOKIA") && contenedor.substring(0, 3).equals("25S"))
				newSerial = contenedor.substring(3, contenedor.length());
			else
				newSerial = contenedor;
			serial = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
					+ newSerial.replace(" ", "%20");
			JSONObject jsonSerial = jsonView(serial);
			revision = jsonSerial.getString("revision");
			JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
			container.setContainer(jsonSerial.getString("serial"));
			container.setQty(jsonSerial.getInt("qty"));
			container.setStatus(1);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = formatter.parse(jsonSerial.getString("dte"));
			container.setDte(date);
			container.setPartNumber(jsonPart.getString("partNumber"));
			int count = 0;
			for (int i = 0; i < containerList.size(); i++)
				if (container.getContainer().equals(containerList.get(i).getContainer()))
					count = count + 1;
			if (count == 0)
				containerList.add(container);
			model.addAttribute("containerList", containerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home/TableDivTicket", model);
	}

	// borrame.. killme

	// @RequestMapping(value = "shit")
	// public ResponseEntity<byte[]> popo() {
	//
	// Document document = null;
	// try {
	// document = new Document();
	// document.setMargins(60, 65, 50, 0);
	// BuildPdf doc = new BuildPdf("ticket", "containers.get(0)getPartNumber()",
	// "project", 100);
	// ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	// try {
	// PdfWriter.getInstance(document, buffer);
	// document.open();
	// doc.buildDoc(document);
	// document.close();
	// } catch (DocumentException e) {
	// e.printStackTrace();
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	//
	//
	//
	//
	//
	// // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
	// byte[] contents = IOUtils.toByteArray(document);
	//
	// HttpHeaders headers = new HttpHeaders();
	// headers.setContentType(MediaType.parseMediaType("application/pdf"));
	// String filename = "output.pdf";
	// headers.setContentDispositionFormData(filename, filename);
	// headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	// ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents,
	// headers, HttpStatus.OK);
	// return response;
	//
	//
	// return document;
	// }

	// ****************

	@RequestMapping(value = "saveTicket", method = RequestMethod.POST)
	public @ResponseBody Boolean SaveTicket(Boolean status, @RequestBody List<tblContainerJSON> listContainers,
			@RequestParam String project, @RequestParam String ticket) {
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			Project tempProject = projectDao.findProjectByProject(project);
			PrintZPL pz = new PrintZPL(zpldao);
			boolean stateZPL = false;
			boolean RMA = false;
			System.out.println("Proyecto: " + tempProject.getProjectName());
			tempTicket.setProjectId((int) tempProject.getIdProject());
			tempTicket.setEnabled("1");
			tempTicket.setQtyContainers(containerList.size());
			if (containerList.get(0).getPartNumber().contains("-RM"))
				RMA = true;
			if (project.equals("HAAS"))
				stateZPL = pz.printHAAS(tempTicket.getTicket(), containerList, tempProject.getIdProject(),
						tempProject.getProjectName(), RMA);
			else if (project.equals("COLOR KINETICS BLAST") && project.equals("COLOR KINETICS REACH"))
				stateZPL = pz.printColor(tempTicket.getTicket(), containerList, tempProject.getIdProject(),
						tempProject.getProjectName());
			else if (project.equals("NOKIA"))
				stateZPL = pz.printNOKIA(tempTicket.getTicket(), containerList, tempProject.getIdProject(),
						tempProject.getProjectName(), revision);
			else if (project.equals("DIALIGHT"))
				stateZPL = pz.printDIALIGHT(tempTicket.getTicket(), containerList, tempProject.getIdProject(),
						tempProject.getProjectName());
			else if (project.equals("SIEMENS"))
				stateZPL = pz.printSIEMENS(tempTicket.getTicket(), containerList, tempProject.getIdProject(),
						tempProject.getProjectName(), RMA);
			else if (project.equals("MCE"))
				stateZPL = pz.printMCE(tempTicket.getTicket(), containerList, tempProject.getIdProject(),
						tempProject.getProjectName());
			if (stateZPL == true) {
				ticketDao.updateTicket(tempTicket);
				for (int i = 0; i < containerList.size(); i++) {
					Container tempContainer = containerList.get(i);
					tempContainer.setIdTicket(tempTicket.getIdTicket());
					tempContainer.setDatafileCree(0);
					containerDao.saveContainer(tempContainer);
				}
				status = true;
			} else
				status = false;

		} catch (Exception e) {
			e.printStackTrace();
		}
		containerList.clear();
		partNumber = "";
		return status;
	}

	@RequestMapping(value = "reprintTcicket", method = RequestMethod.POST)
	public @ResponseBody int ReprintTcicket(@RequestParam("ticket") String ticket) {
		int status;
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			containerList = containerDao.findContainersByTicket(tempTicket);
			boolean stateZPL = false;
			boolean RMA = false;
			PrintZPL pz = new PrintZPL(zpldao);
			if (containerList.get(0).getPartNumber().contains("-RM"))
				RMA = true;
			if (tempTicket.getEnabled().contains("1")) {
				if (tempTicket.getProject().getProjectName().equals("HAAS"))
					stateZPL = pz.printHAAS(tempTicket.getTicket(), containerList, tempTicket.getProjectId(),
							tempTicket.getProject().getProjectName(), RMA);
				else if (tempTicket.getProject().getProjectName().equals("COLOR KINETICS BLAST")
						&& tempTicket.getProject().getProjectName().equals("COLOR KINETICS REACH"))
					stateZPL = pz.printColor(tempTicket.getTicket(), containerList, tempTicket.getProjectId(),
							tempTicket.getProject().getProjectName());
				else if (tempTicket.getProject().getProjectName().equals("NOKIA"))
					stateZPL = pz.printNOKIA(tempTicket.getTicket(), containerList, tempTicket.getProjectId(),
							tempTicket.getProject().getProjectName(), revision);
				else if (tempTicket.getProject().getProjectName().equals("DIALIGHT"))
					stateZPL = pz.printDIALIGHT(tempTicket.getTicket(), containerList, tempTicket.getProjectId(),
							tempTicket.getProject().getProjectName());
				if (stateZPL == false)
					status = 0;
				else
					status = 1;
			} else
				status = 0;
		} catch (Exception e) {
			status = 0;
			e.printStackTrace();
		}
		containerList.clear();
		partNumber = "";
		return status;
	}

	@RequestMapping(value = "clearContainerList", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ClearListContainer(ModelMap model) {
		try {
			containerList.clear();
			partNumber = "";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home/TableDivTicket", model);
	}

	@RequestMapping(value = "obtenerTicketInfo", method = RequestMethod.POST)
	public @ResponseBody ModelAndView obtenerTicketInfo(ModelMap model, @RequestParam("numTicket") String numTicket) {
		List<Container> listContainer = new ArrayList<Container>();
		Ticket ticket = ticketDao.findTicketByTicket(numTicket);
		try {
			if (ticket.getEnabled().equals("0"))
				model.addAttribute("ticketEnabled", "Sin asignar");
			if (ticket.getEnabled().equals("1"))
				model.addAttribute("ticketEnabled", "Piso de producción");
			if (ticket.getEnabled().equals("2"))
				model.addAttribute("ticketEnabled", "Embarques");
			if (ticket.getEnabled().equals("3"))
				model.addAttribute("ticketEnabled", "Cargando a troca");
			if (ticket.getEnabled().equals("4"))
				model.addAttribute("ticketEnabled", "Enviado a troca");
			model.addAttribute("ticket", ticket);
			listContainer = containerDao.findContainersByTicket(ticket);
			model.addAttribute("container", listContainer);
			model.addAttribute("totalContainers", listContainer.size());
			return new ModelAndView("BuscarTicket/TableDiv", model);
		} catch (NullPointerException e) {
			return new ModelAndView("home/notFound", model);
		}
	}

	@RequestMapping(value = "obtenerContenedorInfo", method = RequestMethod.POST)
	public @ResponseBody ModelAndView obtenerContenedorInfo(ModelMap model,
			@RequestParam("numContainer") String numContainer) {
		try {
			Container findContainer;
			Ticket ticket;
			findContainer = containerDao.findContainerByContainer(numContainer);
			ticket = ticketDao.findTicketByIdTicket(findContainer.getIdTicket());
			container = containerDao.findContainersByTicket(ticket);
			if (ticket.getEnabled().equals("0"))
				model.addAttribute("ticketEnabled", "Sin asignar");
			if (ticket.getEnabled().equals("1"))
				model.addAttribute("ticketEnabled", "Piso de producción");
			if (ticket.getEnabled().equals("2"))
				model.addAttribute("ticketEnabled", "Embarques");
			if (ticket.getEnabled().equals("3"))
				model.addAttribute("ticketEnabled", "Cargando a troca");
			if (ticket.getEnabled().equals("4"))
				model.addAttribute("ticketEnabled", "Enviado a troca");
			model.addAttribute("ticket", ticket);
			model.addAttribute("container", container);
			return new ModelAndView("BuscarTicket/TableDiv", model);
		} catch (NullPointerException ex) {
			return new ModelAndView("home/notFound", model);
		}
	}

	@RequestMapping(value = "cleanSearchList", method = RequestMethod.POST)
	public @ResponseBody ModelAndView ClearSearchList(ModelMap model) {
		try {
			container.clear();
		} catch (NullPointerException ex) {
			System.out.println("nullPointerException en limpiar lista");
		}
		return new ModelAndView("BuscarTicket/TableDiv", model);
	}

	@RequestMapping(value = "openTicket", method = RequestMethod.POST)
	public @ResponseBody ModelAndView OpenTicket(ModelMap model, @RequestParam("txtOpenTicket") String numTicket) {
		Ticket ticket;
		try {
			ticket = ticketDao.findTicketByTicket(numTicket);
			containerList = containerDao.findContainersByTicket(ticket);
			if (ticket == null)
				ticket = new Ticket(new Project("NOT FOUND"), 0);
			model.addAttribute("numTicket", ticket.getTicket());
			model.addAttribute("containerList", containerList);
		} catch (NullPointerException e) {
			System.out.println("No se encontrarron tickets.");
			return new ModelAndView("home/notFound", model);
		}
		return new ModelAndView("home/TableDivTicket", model);
	}

	@RequestMapping(value = "loadProject", method = RequestMethod.POST)
	public @ResponseBody String LoadProject(@RequestParam("ticket") String numTicket) {
		String project = "";
		try {
			Ticket ticket;
			ticket = ticketDao.findTicketByTicket(numTicket);
			if (ticket != null)
				project = ticket.getProject().getProjectName();
			else
				project = "Error";
		} catch (NullPointerException e) {
			return project;
		}
		return project;
	}

	@RequestMapping(value = "getProjects")
	public @ResponseBody List<Project> loadSelect() {
		return (List<Project>) projectDao.findAllProjects();
	}

	public JSONObject jsonView(String url) throws IOException {
		JSONObject jsonobject = null;
		try {
			URL TempUrl = new URL(url);
			URLConnection urlConnection = TempUrl.openConnection();
			urlConnection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			JSONArray jsonarray = null;
			while ((inputLine = br.readLine()) != null)
				jsonarray = new JSONArray("[" + inputLine + "]");
			br.close();
			if (jsonarray.length() != 0)
				for (int i = 0; i < jsonarray.length(); i++)
					jsonobject = jsonarray.getJSONObject(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonobject;
	}
}
