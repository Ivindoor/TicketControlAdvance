package com.sanmina.tk.web;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanmina.tk.config.LoginInActiveDirectory;
import com.sanmina.tk.orm.dao.ContainerDao;
import com.sanmina.tk.orm.dao.EchoStarValidDao;
import com.sanmina.tk.orm.dao.MappedPartNumberDao;
import com.sanmina.tk.orm.dao.PoLineDao;
import com.sanmina.tk.orm.dao.ProjectDao;
import com.sanmina.tk.orm.dao.TicketDao;
import com.sanmina.tk.orm.dao.UserDao;
import com.sanmina.tk.orm.dao.ZPLDao;
import com.sanmina.tk.orm.dao.rmx.UnitDao;
import com.sanmina.tk.orm.dao.skid.ContainerSkidDao;
import com.sanmina.tk.orm.dao.skid.PartNumberSkidDao;
import com.sanmina.tk.orm.dao.skid.SerialSkidDao;
import com.sanmina.tk.orm.model.Container;
import com.sanmina.tk.orm.model.ContainerSerial;
import com.sanmina.tk.orm.model.ContainersOfSkid;
import com.sanmina.tk.orm.model.MappedPartNumber;
import com.sanmina.tk.orm.model.PartialPallet;
import com.sanmina.tk.orm.model.PoLine;
import com.sanmina.tk.orm.model.Project;
import com.sanmina.tk.orm.model.SerialsOfContainer;
import com.sanmina.tk.orm.model.Ticket;
import com.sanmina.tk.orm.model.TicketPartial;
import com.sanmina.tk.orm.model.Users;
import com.sanmina.tk.orm.model.tblContainerJSON;
import com.sanmina.tk.orm.model.rmx.Unit;
import com.sanmina.tk.orm.model.skid.ContainerSkid;
import com.sanmina.tk.orm.model.skid.PartNumberSkid;
import com.sanmina.tk.orm.model.skid.SerialSkid;
import com.sanmina.tk.pdf.BuildPdf;
import com.sanmina.tk.pdf.BuildPdfCOLOR;
import com.sanmina.tk.pdf.BuildPdfCREE;
import com.sanmina.tk.pdf.BuildPdfDishPartial;
import com.sanmina.tk.pdf.BuildPdfEcho;
import com.sanmina.tk.zpl.PrintZPL;

@Controller
public class RequestDispatcher {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private ContainerDao containerDao;
	@Autowired
	private ContainerSkidDao containerSkidDao;
	@Autowired
	private ZPLDao zpldao;
	@Autowired
	private PoLineDao poLineDao;
	@Autowired
	private SerialSkidDao serialSkidDao;
	@Autowired
	private PartNumberSkidDao partNumberSkidDao;
	@Autowired
	private MappedPartNumberDao mappedDao;
	@Autowired
	private EchoStarValidDao echoStarValidDao;
	@Autowired
	private UnitDao unitDao;

	public void registerUseMetric(String method, Integer qty) {
		  try {
				String campusAPI = "http://gdl1amwebl03.sanmina.com/WebService_APPM";
		      SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		      factory.setConnectTimeout(1000);
		      factory.setReadTimeout(1000);
		      RestTemplate restTemplateMetric = new RestTemplate(factory);
		      restTemplateMetric.getForObject(campusAPI + "/App/App/SaveLog/210/9/" + method, String.class);//idPlant2 = 9
		      // restTemplateMetric.getForObject(campusAPI + "/App/App/SaveLog/PhoneBook/8/" +
		      // method+"/1",
		      // String.class);
		  } catch (Exception e) {
		      // Nothing
		  }
		}
	
	@RequestMapping(value = "/")
	public ModelAndView landing() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/index")
	public ModelAndView landingResolv(ModelMap model, HttpServletRequest request) {
		try {
			String currentUser = (String) request.getSession().getAttribute("LOGGEDIN_USER");
			System.out.println(currentUser);
			if (currentUser == null) {
				Users current = userDao.findUserByUserLogin(currentUser);
				model.addAttribute("currentUser", current.getUniversalLogin());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("index", model);
	}

	@RequestMapping(value = "home")
	public ModelAndView landingResolv(ModelMap model) {
		return new ModelAndView("home/home", model);
	}

	@RequestMapping(value = "/login")
	public ModelAndView loginResolv(ModelMap model, HttpServletRequest request) {
		try {
			System.out.println("LOGING");
			model.addAttribute("userCredential", new Users());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(value = "loginUser/userName={userName}/userPassword={userPassword}", method = RequestMethod.GET)
	public @ResponseBody String login(@PathVariable("userName") String userName,
			@PathVariable("userPassword") String userPassword, ModelMap model, HttpServletRequest request) {
		String redirec = "login?error";
		try {
			LoginInActiveDirectory liad = new LoginInActiveDirectory();
			Users userLocal = userDao.findUserByUserLogin(userName);
			if (userLocal != null) {
				if (liad.login(userName, userPassword)) {
					redirec = "index";
					request.getSession().setAttribute("LOGGEDIN_USER", userName);
					request.getSession().setAttribute("ID_USER", userLocal.getId());
					request.getSession().setAttribute("LEVEL_USER", userLocal.getLevelTicket());
					request.getSession().setMaxInactiveInterval(0);
				} else {
					if (userLocal.getPassword().equals(userPassword)) {
						redirec = "index";
						request.getSession().setAttribute("LOGGEDIN_USER", userName);
						request.getSession().setAttribute("ID_USER", userLocal.getId());
						request.getSession().setAttribute("LEVEL_USER", userLocal.getLevelTicket());
						request.getSession().setMaxInactiveInterval(0);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return redirec;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@RequestMapping(value = "levelAdmin")
	public @ResponseBody String levelAdmin(HttpServletRequest request) {
		String levelTicket = "";
		try {
			String userName = (String) request.getSession().getAttribute("LOGGEDIN_USER");
			Users validate = userDao.findUserByUserLogin(userName);
			if (validate.getLevelTicket() != null) {
				levelTicket = validate.getLevelTicket().toString();
			} else {
				levelTicket = "False";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levelTicket;
	}

	@RequestMapping(value = "ticketAvailable")
	public @ResponseBody String ticketAvailable() {
		String ticketAvailable = "";
		try {
			Ticket ticket;
			ticket = ticketDao.findTicketAvailable();
			ticket.setEnabled("1");
			ticketDao.updateTicket(ticket);
			ticketAvailable = ticket.getTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticketAvailable;
	}

	@RequestMapping(value = "restartValues")
	public String restartValues() {
		return "home/TableDivTicket";
	}

	@RequestMapping(value = "restartPartial")
	public String restartPartial() {
		return "home/TableDivPartial";
	}

	@RequestMapping(value = "validateTicketOpen")
	public @ResponseBody String validateTicketOpen(String ticket) {
		String vMsg = "";
		try {
			if (ticketDao.findTicketByTicket(ticket) == null) {
				vMsg = "Ticket don't exist";
			} else {
				Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
				if (tempTicket.getEnabled().equals("1")) {
					vMsg = "1";
				} else if (tempTicket.getEnabled().equals("2")) {
					vMsg = "2";
				} else if (tempTicket.getEnabled().equals("3")) {
					vMsg = "3";
				} else if (tempTicket.getEnabled().equals("4")) {
					vMsg = "4";
				} else if (tempTicket.getEnabled().equals("0")) {
					vMsg = "0";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "openTicket", method = RequestMethod.POST)
	public @ResponseBody ModelAndView openTicket(ModelMap model, String ticket) {
		Ticket tempTicket = new Ticket();
		try {
			if (ticketDao.findTicketByTicket(ticket) != null) {
				tempTicket = ticketDao.findTicketByTicket(ticket);
				if (!tempTicket.getEnabled().equals("0") && tempTicket.getProjectId() != 4) {
					Project project = projectDao.findProjectByIdProject(tempTicket.getProjectId());
					List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
					model.addAttribute("project", project.getProjectName());
					model.addAttribute("containerList", containerList);
				} else {
					return null;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home/TableDivTicket", model);
	}

	@RequestMapping(value = "loadProject", method = RequestMethod.POST)
	public @ResponseBody String LoadProject(String ticket) {
		String vMsg = "";
		try {
			Ticket tempTicket = new Ticket();
			if (ticketDao.findTicketByTicket(ticket) != null) {
				tempTicket = ticketDao.findTicketByTicket(ticket);
				Project project = new Project();
				if (projectDao.findProjectByIdProject(tempTicket.getProjectId()) != null) {
					project = projectDao.findProjectByIdProject(tempTicket.getProjectId());
					vMsg = "Ok" + project.getProjectName();
				} else {
					vMsg = "El ticket no tiene un proyecto asignado";
				}
			} else {
				vMsg = "El ticket no existe en la base de datos";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "reprintTicket", method = RequestMethod.POST)
	public @ResponseBody String reprintTicket(String ticket, @RequestBody List<tblContainerJSON> containers) {
		String vMsg = "";
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			Project project = projectDao.findProjectByIdProject(tempTicket.getProjectId());
			PrintZPL stateZPL = new PrintZPL(zpldao);
			if (project.getProjectName().contains("DIALIGHT")) {
				List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
				String partNumber = containerList.get(0).getPartNumber();
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				vMsg = stateZPL.printDIALIGHT(ticket, containerList, tempTicket.getProjectId(), partNumber);
			} else if (project.getProjectName().contains("IUSA")) {
				List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
				String partNumber = containerList.get(0).getPartNumber();
				long qty = 0;
				for (int i = 0; i < containerList.size(); i++) {
					qty = qty + containerList.get(i).getQty();
				}
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Part/PartNumber/FindDescriptionByPartNumber/"
						+ partNumber;
				String description = "";
				if (jsonViewString(url) != null) {
					description = jsonViewString(url);
				}
				vMsg = stateZPL.printIUSA(ticket, tempTicket.getProjectId(), partNumber, qty, description);
			} else if (project.getProjectName().contains("NOKIA FSM4")) {
				List<Container> containersList = containerDao.findContainersByTicket(tempTicket);
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containersList.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String revision = jPart.getString("revision");
				String partNumber = jPart.getString("partNumber");
				int qty = jSerial.getInt("qty");
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				vMsg = stateZPL.printNOKIAFSM4(ticket, partNumber, revision, qty, tempTicket.getProjectId());
			} else if (project.getProjectName().contains("NOKIA RRH")) {
				List<Container> containersList = containerDao.findContainersByTicket(tempTicket);
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containersList.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String revision = jPart.getString("revision");
				String partNumber = jPart.getString("partNumber");
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				vMsg = stateZPL.printNOKIARRH(ticket, containersList, project.getIdProject(), project.getProjectName(),
						revision, partNumber);
			} else if (project.getProjectName().contains("COLOR KINETICS")) {
				List<Container> containersList = containerDao.findContainersByTicket(tempTicket);
				for (int i = 0; i < containersList.size(); i++) {
					System.out.println(containersList.get(i).getPartNumber());
				}
				vMsg = stateZPL.printColor(ticket, containersList, project.getIdProject(), project.getProjectName());
			} else if (project.getProjectName().contains("DIGITAL LUMENS")) {
				List<Container> containersList = containerDao.findContainersByTicket(tempTicket);
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containersList.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String modelDescription = jPart.getString("modelDescription");
				vMsg = stateZPL.printDigital(ticket, containersList, project.getIdProject(), project.getProjectName(),
						modelDescription);
			} else if (project.getProjectName().contains("HAAS")) {
				List<Container> containersList = containerDao.findContainersByTicket(tempTicket);
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containersList.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String modelDescription = jPart.getString("description");
				String partNumber = jPart.getString("partNumber");
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				System.out
						.println("El numero de parte a imprimir " + partNumber + " la descripcion " + modelDescription);
				vMsg = stateZPL.printHAAS(ticket, containersList, project.getIdProject(), project.getProjectName(),
						modelDescription, partNumber);
			} else if (project.getProjectName().contains("SIEMENS")) {
				List<Container> containersList = containerDao.findContainersByTicket(tempTicket);
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containersList.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String modelDescription = jPart.getString("description");

				if (vMsg == "") {

					vMsg = stateZPL.printSIEMENS(ticket, containersList, project.getIdProject(),
							project.getProjectName(), modelDescription);
				}
			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "addTicketAccessories", method = RequestMethod.POST)
	public @ResponseBody String addTicketAccessories(String ticket, Integer qty, String part) {
		String vMsg = "";
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket.trim());
			List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
			if (containerList.isEmpty()) {

			} else {

			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "cleanTicket", method = RequestMethod.POST)
	public @ResponseBody String cleanTicket(String ticket, @RequestBody List<tblContainerJSON> containers) {
		String vMsg = "";
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			tempTicket.setEnabled("0");
			ticketDao.updateTicket(tempTicket);
			for (int i = 0; i < containers.size(); i++) {
				Container tempContainer = containerDao.findContainerByContainer(containers.get(i).getContenedor());
				containerDao.deleteContainer(tempContainer);
				if (containerDao.findContainerByContainer(containers.get(i).getContenedor()) == null) {
					vMsg = "Ok";
				}
			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "addContainer", method = RequestMethod.POST)
	public @ResponseBody ModelAndView addContainer(ModelMap model, String container, String ticket) {
		try {
			registerUseMetric("Ticket Processed",1);
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
			model.addAttribute("containerList", containerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home/TableDivTicket", model);
	}

	@RequestMapping(value = "startEngine", method = RequestMethod.POST)
	public @ResponseBody Integer startEngine(String poLine) {
		int qtyRequired = 0;
		try {
			PoLine pLine = poLineDao.findPoLineByPoLine(poLine);
			qtyRequired = pLine.getPartQtyRequired();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qtyRequired;
	}

	@RequestMapping(value = "qtyStored", method = RequestMethod.POST)
	public @ResponseBody Integer QtyStored(String poLine) {
		int qtyStored = 0;
		try {
			PoLine pLine = poLineDao.findPoLineByPoLine(poLine);
			qtyStored = pLine.getPartQtyStored();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return qtyStored;
	}

	@RequestMapping(value = "searchSerialByContainer", method = RequestMethod.POST)
	public @ResponseBody ModelAndView searchSerialByContainer(ModelMap model, String container) {
		try {
			List<ContainersOfSkid> LstContainers = new ArrayList<ContainersOfSkid>();
			List<JSONObject> jContainers = new ArrayList<JSONObject>();
			String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerGeneral/"
					+ container.trim().toUpperCase();
			if (jsonViewList(url) != null) {
				jContainers = jsonViewList(url);
				for (int x = 0; x < jContainers.size(); x++) {
					int id = (Integer) jContainers.get(x).get("id");
					int qty = (Integer) jContainers.get(x).get("quantity");
					String partNumber = jContainers.get(x).get("partNumber").toString();
					String serialNumber = jContainers.get(x).get("serialNumber").toString();
					ContainersOfSkid cont = new ContainersOfSkid();
					cont.setID(id);
					cont.setPartNumber(partNumber);
					cont.setQuantity(qty);
					cont.setSerialNumber(serialNumber);
					LstContainers.add(cont);
				}
			}
			model.addAttribute("LstContainers", LstContainers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home/TblDivValidation", model);
	}

	@RequestMapping(value = "echoValid")
	public @ResponseBody ModelAndView echoValid(ModelMap model, String container) {
		try {
			List<ContainersOfSkid> LstContainers = new ArrayList<ContainersOfSkid>();
			List<JSONObject> jContainers = new ArrayList<JSONObject>();
			String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerGeneral/"
					+ container.trim().toUpperCase();
			if (jsonViewList(url) != null) {
				jContainers = jsonViewList(url);
				for (int x = 0; x < jContainers.size(); x++) {
					int id = (Integer) jContainers.get(x).get("id");
					int qty = (Integer) jContainers.get(x).get("quantity");
					String partNumber = jContainers.get(x).get("partNumber").toString();
					String serialNumber = jContainers.get(x).get("serialNumber").toString();
					ContainersOfSkid cont = new ContainersOfSkid();
					cont.setID(id);
					cont.setPartNumber(partNumber);
					cont.setQuantity(qty);
					cont.setSerialNumber(serialNumber);
					LstContainers.add(cont);
				}
			}
			model.addAttribute("LstContainers", LstContainers);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("home/TblDivValidation", model);
	}

	@RequestMapping(value = "skidInsert")
	public @ResponseBody String skidInsert(String skid, String ticket) {
		String vMsg = "";
		try {
			String urlList = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerGeneral/"
					+ skid.trim().toUpperCase();
			List<JSONObject> jList = jsonViewList(urlList);
			String partNumber = jList.get(0).get("partNumber").toString();
			int qty = 0;
			for (int i = 0; i < jList.size(); i++) {
				qty = qty + (Integer) jList.get(i).get("quantity");
			}
			String urlObject = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
					+ skid.trim().toUpperCase();
			JSONObject jObject = jsonView(urlObject);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			if (!jObject.get("dte").toString().equals("null")) {
				date = formatter.parse(jObject.get("dte").toString());
			}
			Ticket tempTk = new Ticket();
			tempTk = ticketDao.findTicketByTicket(ticket);
			Container container = new Container();
			container.setContainer(skid.trim().toUpperCase());
			container.setPartNumber(partNumber);
			container.setStatus(1);
			container.setQty(qty);
			container.setDte(date);
			container.setIdTicket(tempTk.getIdTicket());
			containerDao.saveContainer(container);
			vMsg = "Ok";
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "coreEchoStar", method = RequestMethod.POST)
	public @ResponseBody String coreEchoStar(String container, String ticket, String project) {
		String vMsg = "";
		try {
			if (containerDao.findContainerByContainer(container.trim().toUpperCase()) != null) {
				Container rContainer = containerDao.findContainerByContainer(container.trim().toUpperCase());
				Ticket rTicket = ticketDao.findTicketByIdTicket(rContainer.getIdTicket());
				vMsg = "El skid ya extiste en el ticket: " + rTicket.getTicket();
			} else {
				Ticket tempTicket = ticketDao.findTicketByTicket(ticket.toUpperCase().trim());
				if (tempTicket != null) {
					List<Container> containerList = new ArrayList<Container>();
					if (!project.contains("VERIZON")) {
						containerList = containerDao.findContainersByTicket(tempTicket);
					}
					if (containerList.isEmpty()) {
						String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
								+ container.trim().toUpperCase();
						JSONObject jSerial = new JSONObject();
						String partNumber = "";
						if (jsonView(url) != null) {
							jSerial = (JSONObject) jsonView(url);
							JSONObject jPart = new JSONObject();

							String partsOfPallet = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/DistinctPartByContainer/"
									+ container.trim().toUpperCase();
							List<JSONObject> distinctParts = (List<JSONObject>) jsonViewList(partsOfPallet);
							System.out.println("size de la lista de los numeros de parte " + distinctParts.size());
							if (!jSerial.isNull("partNumber")) {
								jPart = jSerial.getJSONObject("partNumber");
								partNumber = jPart.getString("partNumber");
							} else {
								partNumber = distinctParts.get(0).getString("partNumber").substring(0, 11);
							}

							if (echoStarValidDao.findEchoStarValidByPartNumber(partNumber) != null
									&& !project.equals("VERIZON")) {
								vMsg = "validation";
							} else {
								int qty = (Integer) jSerial.get("qty");
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date date = new Date();
								if (!jSerial.get("dte").toString().equals("null")) {
									date = formatter.parse(jSerial.get("dte").toString());
								}
								Container tempContainer = new Container();
								tempContainer.setContainer(container.trim().toUpperCase());
								tempContainer.setIdTicket(tempTicket.getIdTicket());
								tempContainer.setPartNumber(partNumber);

								if (partNumber.contains("LFDH-212160") || project.contains("VERIZON")) {
									qty = 0;
									String urlBoxes = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerGeneral/"
											+ container.trim().toUpperCase();
									List<JSONObject> boxes = (List<JSONObject>) jsonViewList(urlBoxes);
									List<ContainersOfSkid> LstContainers = new ArrayList<ContainersOfSkid>();
									if (jsonViewList(urlBoxes) != null) {
										for (int x = 0; x < boxes.size(); x++) {
											int id = (Integer) boxes.get(x).get("id");
											int qtycont = (Integer) boxes.get(x).get("quantity");
											String jPartNumber = boxes.get(x).get("partNumber").toString();
											String serialNumber = boxes.get(x).get("serialNumber").toString();
											ContainersOfSkid cont = new ContainersOfSkid();
											cont.setID(id);
											cont.setPartNumber(jPartNumber);
											cont.setQuantity(qtycont);
											cont.setSerialNumber(serialNumber);
											LstContainers.add(cont);
										}
									}
									for (int y = 0; y < LstContainers.size(); y++) {
										System.out.println(
												"cantidad de la box " + y + ": " + LstContainers.get(y).getQuantity());
										qty = qty + LstContainers.get(y).getQuantity();
									}
								}

								String urlToJob = "";
								if (partNumber.contains("LFDH-212160") || project.contains("VERIZON")) {
									urlToJob = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsBySkid/"
											+ container.replace(" ", "%20").toUpperCase().trim();
								} else {

									urlToJob = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainer/"
											+ container.replace(" ", "%20").toUpperCase().trim();
								}
								List<JSONObject> jsonSerials = new ArrayList<JSONObject>();
								List<String> serialsShop = new ArrayList<String>();
								jsonSerials = jsonViewList(urlToJob);
								for (int i = 0; i < jsonSerials.size(); i++) {
									System.out.println(jsonSerials.get(i).get("serial_number") + " "
											+ jsonSerials.get(i).get("shop_order_key").toString());
									if (jsonSerials.get(i).get("shop_order_key").toString().contains("null")) {
										serialsShop.add(jsonSerials.get(i).get("serial_number").toString());
									}
								}
								if (!serialsShop.isEmpty()) {
									for (int y = 0; y < serialsShop.size(); y++) {
										if (vMsg.equals("")) {
											vMsg = "Seriales sin shop order " + serialsShop.get(y);
										} else {
											vMsg = vMsg + ", " + serialsShop.get(y);
										}
									}
								}
								if (distinctParts.size() > 1)
									vMsg = "Inserccion de multiples tickets";
								if (vMsg.equals("")) {
									tempContainer.setQty(qty);
									tempContainer.setStatus(1);
									tempContainer.setDte(date);
									containerDao.saveContainer(tempContainer);
									vMsg = "Ok";
								}
							}
						} else {
							vMsg = "Serial not found in MESR 42Q";
						}
					} else {
						vMsg = "This ticket only allows one skid";
					}
				} else {
					vMsg = "This ticket is null";
				}
			}
			System.out.println("Mensaje de retorno de vMsg = " + vMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "multipleContainer", method = RequestMethod.POST)
	public @ResponseBody ModelAndView addContainer(ModelMap model, String container, String ticket, String project) {
		try {

			List<PartialPallet> partialList = new ArrayList<>();

			String partsCountOfPallet = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/DistinctPartCountByContainer/"
					+ container.trim().toUpperCase();

			List<JSONObject> distinctPartsCount = (List<JSONObject>) jsonViewList(partsCountOfPallet);

			Ticket firstTicket = ticketDao.findTicketByTicket(ticket);

			Container firstContainer = new Container();
			firstContainer.setContainer(container);
			firstContainer.setDatafileCree(0);
			firstContainer.setDte(new Date());
			firstContainer.setIdTicket(firstTicket.getIdTicket());
			firstContainer.setPartNumber(distinctPartsCount.get(0).getString("partNumber"));
			firstContainer.setQty(distinctPartsCount.get(0).getInt("qty"));
			firstContainer.setStatus(1);
			containerDao.saveContainer(firstContainer);

			PartialPallet firstPartial = new PartialPallet();
			firstPartial.setContainer(container);
			firstPartial.setPartNumber(distinctPartsCount.get(0).getString("partNumber"));
			firstPartial.setQty(distinctPartsCount.get(0).getInt("qty"));
			firstPartial.setTicket(ticket);

			partialList.add(firstPartial);

			for (int i = 1; i < distinctPartsCount.size(); i++) {
				Ticket otherTicket;
				otherTicket = ticketDao.findTicketAvailable();
				otherTicket.setEnabled("1");
				ticketDao.updateTicket(otherTicket);

				Container otherContainer = new Container();
				otherContainer.setContainer(container + "_" + i);
				otherContainer.setPartNumber(distinctPartsCount.get(i).getString("partNumber"));
				otherContainer.setQty(distinctPartsCount.get(i).getInt("qty"));
				otherContainer.setStatus(1);
				otherContainer.setIdTicket(otherTicket.getIdTicket());
				otherContainer.setDte(new Date());
				otherContainer.setDatafileCree(0);
				containerDao.saveContainer(otherContainer);

				PartialPallet otherPartial = new PartialPallet();
				otherPartial.setContainer(otherContainer.getContainer());
				otherPartial.setPartNumber(distinctPartsCount.get(i).getString("partNumber"));
				otherPartial.setQty(distinctPartsCount.get(i).getInt("qty"));
				otherPartial.setTicket(otherTicket.getTicket());
				partialList.add(otherPartial);
			}

			model.addAttribute("partialList", partialList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ModelAndView("home/TableDivPartial", model);
	}

	@RequestMapping(value = "coreGBRMA", method = RequestMethod.POST)
	public @ResponseBody String coreGBRMA(String container, String ticket, String project, String po) {
		String vMsg = "";
		try {
			if (containerDao.findContainerByContainer(container.trim().toUpperCase()) != null) {
				Container rContainer = containerDao.findContainerByContainer(container.trim().toUpperCase());
				Ticket rTicket = ticketDao.findTicketByIdTicket(rContainer.getIdTicket());
				vMsg = "El serial ya extiste en el ticket: " + rTicket.getTicket();
			} else {
				Unit unit = unitDao.findUnitBySerial(container.trim().toUpperCase());
				if (unit != null) {
					if (unit.getPurchaseOrder().equals(po.trim().toUpperCase())) {

					} else {
						vMsg = "La PO ingresada es dintinta a la PO en el rmx_2";
					}
				} else {
					vMsg = "El serial no esta asignado a un PO en el rmx_2";
				}
			}
			System.out.println("Mensaje de retorno de vMsg = " + vMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "comparePartNumber", method = RequestMethod.POST)
	public @ResponseBody String comparePartNumber(String partNumber, String container, String ticket, String project,
			String poLine) {
		String vMsg = "";
		try {
			if (project.contains("IUSA")) {
				System.out.println(ticket);
				Ticket tempTicket = this.ticketDao.findTicketByTicket(ticket);
				List<Container> serials = this.containerDao.findContainersByTicket(tempTicket);
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ container.trim().toUpperCase();
				System.out.println("IUSA URL " + url);
				JSONObject jSerial = jsonView(url);
				System.out.println(jSerial);
				if (jSerial == null) {
					vMsg = "El serial no fue ecnontrado o las sessiones estan ocupadas en el WebService42Q, vuelva a ingresar el serial de nuevo";
				} else {
					JSONObject jPart = (JSONObject) jSerial.get("partNumber");
					if (jPart == null) {
						vMsg = "EL serial no tiene asignado un numero de parte en el WebService42Q";
					} else {
						String part = jPart.getString("partNumber");
						int qty = jSerial.getInt("qty");
						if (serials.size() > 0) {
							if (((Container) serials.get(0)).getPartNumber().equals(part)) {
								Container newContainer = new Container();
								newContainer.setContainer(container.trim().toUpperCase());
								newContainer.setDte(new Date());
								newContainer.setPartNumber(part);
								newContainer.setQty(qty);
								newContainer.setStatus(1);
								newContainer.setIdTicket(tempTicket.getIdTicket());
								this.containerDao.saveContainer(newContainer);
								vMsg = "OK";
							} else {
								vMsg = "El numero de parte no coincide con el resto de los seriales";
							}
						} else {
							Container newContainer = new Container();
							newContainer.setContainer(container.trim().toUpperCase());
							newContainer.setDte(new Date());
							newContainer.setPartNumber(part);
							newContainer.setQty(qty);
							newContainer.setStatus(1);
							newContainer.setIdTicket(tempTicket.getIdTicket());
							this.containerDao.saveContainer(newContainer);
							vMsg = "OK";
						}
					}
				}
			} else if (project.contains("COLOR KINETICS")) {
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ container.replace(" ", "%20").toUpperCase().trim();
				JSONObject jsonSerial = new JSONObject();
				if (jsonView(url) == null) {
					vMsg = "El serial no fue ecnontrado o las sessiones estan ocupadas en el WebService42Q, vuelva a ingresar el serial de nuevo";
				} else {
					jsonSerial = jsonView(url);
					JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
					if (jsonPart == null) {
						vMsg = "EL serial no tiene asignado un numero de parte en el WebService42Q";
					} else {
						vMsg = "OK";
						Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
						Container tempContainer = new Container();
						tempContainer.setContainer(container.trim().toUpperCase());
						tempContainer.setPartNumber(jsonPart.getString("partNumber"));
						tempContainer.setQty(jsonSerial.getLong("qty"));
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = new Date();
						if (!jsonSerial.get("dte").toString().equals("null"))
							date = formatter.parse(jsonSerial.get("dte").toString());

						tempContainer.setDte(date);
						tempContainer.setStatus(1);
						tempContainer.setIdTicket(tempTicket.getIdTicket());
						containerDao.saveContainer(tempContainer);
					}
				}
			} else {
				String url = "";
				if (project.contains("DIALIGHT") && !project.contains("DIALIGHT PO ACCESORIOS")) {
					String temppartNumber = partNumber;
					if (partNumber.contains("LFDGT")) {
						temppartNumber = partNumber.substring(6, partNumber.length());
					}
					if (temppartNumber.contains("-SC")) {
						temppartNumber = temppartNumber.replace("-SC", "");
					} else if (temppartNumber.contains("-C")) {
						temppartNumber = temppartNumber.replace("-C", "");
					}
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ temppartNumber + container.replace(" ", "%20").toUpperCase().trim();

				} else if (project.equals("NOKIA RRH")) {
					container = container.substring(3, container.length());
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ container.replace(" ", "%20").toUpperCase().trim();
				} else if (project.contains("NOKIA FSM4")) {
					container = container.substring(2, container.length());
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ container.replace(" ", "%20").toUpperCase().trim();
				} else {
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ container.replace(" ", "%20").toUpperCase().trim();
				}
				JSONObject jsonSerial = new JSONObject();
				System.out.println(url);
				if (jsonView(url) == null) {
					vMsg = "El serial no fue ecnontrado o las sessiones estan ocupadas en el WebService42Q, vuelva a ingresar el serial de nuevo";
				} else {
					jsonSerial = jsonView(url);
					JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
					if (jsonPart == null) {
						vMsg = "EL serial no tiene asignado un numero de parte en el WebService42Q";
					} else {
						if (project.contains("DIALIGHT")) {
							String partJson = jsonPart.getString("partNumber");
							if (partJson.contains("LFDGT")) {
								partJson = partJson.substring(6, partJson.length());
							}
							if (partNumber.contains("LFDGT")) {
								partNumber = partNumber.substring(6, partNumber.length());
							}
							if (partJson.equals(partNumber)) {
								vMsg = "OK";
								Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
								Container tempContainer = new Container();
								tempContainer.setContainer(container.trim().toUpperCase());
								tempContainer.setPartNumber(jsonPart.getString("partNumber"));
								tempContainer.setQty(jsonSerial.getLong("qty"));
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								Date date = formatter.parse(jsonSerial.getString("dte"));
								tempContainer.setDte(date);
								tempContainer.setStatus(1);
								tempContainer.setIdTicket(tempTicket.getIdTicket());
								containerDao.saveContainer(tempContainer);
							} else {
								vMsg = "El numero de parte no coincide con el resto de los seriales";
							}
						} else {
							Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
							List<Container> listContainer = containerDao.findContainersByTicket(tempTicket);
							if (listContainer.size() > 0) {
								if (jsonPart.getString("partNumber").equals(listContainer.get(0).getPartNumber())) {
									vMsg = "OK";
									Container tempContainer = new Container();
									tempContainer.setContainer(container.trim().toUpperCase());
									tempContainer.setPartNumber(jsonPart.getString("partNumber"));
									tempContainer.setQty(jsonSerial.getLong("qty"));
									SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									Date date = formatter.parse(jsonSerial.getString("dte"));
									tempContainer.setDte(date);
									tempContainer.setStatus(1);
									tempContainer.setIdTicket(tempTicket.getIdTicket());
									containerDao.saveContainer(tempContainer);
								} else {
									vMsg = "El numero de parte no coincide con el resto de los seriales";
								}
							} else {
								vMsg = "OK";
								Container tempContainer = new Container();
								tempContainer.setContainer(container.trim().toUpperCase());
								tempContainer.setPartNumber(jsonPart.getString("partNumber"));
								tempContainer.setQty(jsonSerial.getLong("qty"));
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								System.out.println(jsonSerial.getString("dte"));
								Date date = formatter.parse(jsonSerial.getString("dte"));
								tempContainer.setDte(date);
								tempContainer.setStatus(1);
								tempContainer.setIdTicket(tempTicket.getIdTicket());
								containerDao.saveContainer(tempContainer);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "validateSerialMCE", method = RequestMethod.POST)
	public @ResponseBody String validateSerialMCE(Long container, String ticket, String project) {
		String vMsg = "";
		try {
			System.out.println("este es el contenedor de mce " + container);
			ContainerSkid skidContainer = new ContainerSkid();
			if (containerDao.findContainerByContainer(container.toString()) == null) {
				if (containerSkidDao.findContainerByContainer(container) != null) {
					skidContainer = containerSkidDao.findContainerByContainer(container);
					List<SerialSkid> serials = serialSkidDao.findSerialByContainer(skidContainer.getIdContainer());
					if (serials.size() > 0) {
						Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
						List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
						if (containerList.size() <= 0) {
							Container containerTicket = new Container();
							containerTicket.setContainer(container.toString());
							SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
							Date date = formatter.parse(skidContainer.getDte());
							containerTicket.setDte(date);
							PartNumberSkid part = partNumberSkidDao.findPartByIdPart(serials.get(0).getIdPartNumber());
							containerTicket.setPartNumber(part.getPartNumber());
							containerTicket.setQty(skidContainer.getQtyContainer());
							containerTicket.setIdTicket(tempTicket.getIdTicket());
							containerDao.saveContainer(containerTicket);
							vMsg = "Ok";
						} else {
							PartNumberSkid part = partNumberSkidDao.findPartByIdPart(serials.get(0).getIdPartNumber());
							if (containerList.get(0).getPartNumber().equals(part.getPartNumber())) {
								Container containerTicket = new Container();
								containerTicket.setContainer(container.toString());
								SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
								Date date = formatter.parse(skidContainer.getDte());
								containerTicket.setDte(date);
								containerTicket.setPartNumber(part.getPartNumber());
								containerTicket.setQty(skidContainer.getQtyContainer());
								containerTicket.setIdTicket(tempTicket.getIdTicket());
								containerDao.saveContainer(containerTicket);
								vMsg = "Ok";
							} else {
								vMsg = "El numero de parte de este skid no coincide con el resto";
							}
						}
					} else {
						vMsg = "El skid no tiene seriales asignados en el skid control";
					}
				} else {
					vMsg = "El skid no existe en la base de datos del skid control";
				}
			} else {
				Container cont = containerDao.findContainerByContainer(container.toString());
				Ticket tempTicket = ticketDao.findTicketByIdTicket(cont.getIdTicket());
				vMsg = "El skid ya extiste en el ticket: " + tempTicket.getTicket();
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "validateSerialMES", method = RequestMethod.POST)
	public @ResponseBody String validateSerialMES(String container, String ticket) {
		String vMsg = "";
		try {
			if (this.containerDao.findContainerByContainer(container.trim().toUpperCase()) == null) {
				int count = 0;
				List<SerialsOfContainer> serials = new ArrayList();

				String urlSerials = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerIUSA/"
						+ container.trim().toUpperCase();
				List<JSONObject> lJsonSls = jsonViewList(urlSerials);
				if (!lJsonSls.isEmpty()) {
					for (int i = 0; i < lJsonSls.size(); i++) {
						SerialsOfContainer temp = new SerialsOfContainer();
						temp.setID(Integer.valueOf(((JSONObject) lJsonSls.get(i)).getInt("id")));
						temp.setSerial_number(((JSONObject) lJsonSls.get(i)).get("serial_number").toString());
						serials.add(temp);
					}
				} else {
					vMsg = "This container haven't serials";
				}
				List<ContainerSerial> serialsProcessName = new ArrayList();

				String urlICT = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerIUSASC/"
						+ container.trim().toUpperCase();
				List<JSONObject> lJsonIct = jsonViewList(urlICT);
				if (!lJsonIct.isEmpty()) {
					for (int i = 0; i < lJsonIct.size(); i++) {
						ContainerSerial temp = new ContainerSerial();
						temp.setAction(((JSONObject) lJsonIct.get(i)).get("action").toString());
						temp.setID(Integer.valueOf(((JSONObject) lJsonIct.get(i)).getInt("id")));
						temp.setProcess_name(((JSONObject) lJsonIct.get(i)).get("process_name").toString());
						temp.setSerial_Number(((JSONObject) lJsonIct.get(i)).get("serial_Number").toString());
						serialsProcessName.add(temp);
					}
				}
				List<ContainerSerial> serialsProcessNames = new ArrayList();

				String urlHMU = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerIUSAHMU/"
						+ container.trim().toUpperCase();
				List<JSONObject> lJsonHmu = jsonViewList(urlHMU);
				if (!lJsonHmu.isEmpty()) {
					for (int i = 0; i < lJsonHmu.size(); i++) {
						ContainerSerial temp = new ContainerSerial();
						temp.setAction(((JSONObject) lJsonHmu.get(i)).get("action").toString());
						temp.setID(Integer.valueOf(((JSONObject) lJsonHmu.get(i)).getInt("id")));
						temp.setProcess_name(((JSONObject) lJsonHmu.get(i)).get("process_name").toString());
						temp.setSerial_Number(((JSONObject) lJsonHmu.get(i)).get("serial_Number").toString());
						serialsProcessNames.add(temp);
					}
				}
				String urlSerial = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ container.trim().toUpperCase();
				JSONObject jSerial = jsonView(urlSerial);
				String partNumber = "";
				if (jSerial != null) {
					JSONObject jPart = (JSONObject) jSerial.get("partNumber");
					partNumber = jPart.getString("partNumber");
				}
				if ((partNumber != null) && (!partNumber.isEmpty())) {
					if ((partNumber.equals("IU105602-AE04X01")) || (partNumber.equals("IU105602-AE06X02"))
							|| (partNumber.equals("IU105602-AE06X02")) || (partNumber.equals("IUP4T4V4_BT-PL"))
							|| (partNumber.equals("IUP4T4V19_BT-PL"))) {
						boolean vICT = false;
						List<String> serialNotProccess = new ArrayList();
						for (int x = 0; x < serials.size(); x++) {
							for (int y = 0; y < serialsProcessName.size(); y++) {
								if ((((SerialsOfContainer) serials.get(x)).getSerial_number()
										.equals(((ContainerSerial) serialsProcessName.get(y)).getSerial_Number()))
										&& (((ContainerSerial) serialsProcessName.get(y)).getProcess_name().trim()
												.equals("ICT"))
										&& (((ContainerSerial) serialsProcessName.get(y)).getAction().trim()
												.equals("11"))
										|| (((ContainerSerial) serialsProcessName.get(y)).getProcess_name().trim()
												.equals("P1_ICT"))
												&& (((ContainerSerial) serialsProcessName.get(y)).getAction().trim()
														.equals("11"))) {
									count++;
									vICT = true;
								}
							}
							if (!vICT) {
								serialNotProccess
										.add("el serial " + ((SerialsOfContainer) serials.get(x)).getSerial_number()
												+ " no paso por ICT o P1_ICT");
							} else {
								vICT = false;
							}
						}
						if (serialNotProccess.size() > 0) {
							for (int z = 0; z < serialNotProccess.size(); z++) {
								if (vMsg == "") {
									vMsg = "Serial error: " + (String) serialNotProccess.get(z);
								} else {
									vMsg = vMsg + ", " + (String) serialNotProccess.get(z);
								}
							}
							vMsg = vMsg + ", favor de validar.";
						} else {
							vMsg = "Ok";
						}
						if (count >= serials.size()) {
							vMsg = "Ok";
						} else {
							vMsg = "insuficiente";
						}
					}

					else if ((partNumber.equals("IU105602-AE04X01"))) {
						boolean vHMU = false;
						List<String> serialNotProccess = new ArrayList();
						for (int x = 0; x < serials.size(); x++) {
							for (int y = 0; y < serialsProcessNames.size(); y++) {
								if (((SerialsOfContainer) serials.get(x)).getSerial_number()
										.equals(((ContainerSerial) serialsProcessNames.get(y)).getSerial_Number())) {
									if (( (((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
													.equals("HMU"))
													&& (((ContainerSerial) serialsProcessNames.get(y)).getAction()
															.trim().equals("11")))

											|| ((((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
													.equals("P1_HMU"))
													&& (((ContainerSerial) serialsProcessNames.get(y)).getAction()
															.trim().equals("11")))) {
										vHMU = true;
									}
									System.out.println("Process name del serial "
											+ ((ContainerSerial) serialsProcessNames.get(y)).getProcess_name()
											+ " Serial " + ((SerialsOfContainer) serials.get(x)).getSerial_number());
								}
							}
							if (!vHMU) {
								serialNotProccess
										.add("el serial " + ((SerialsOfContainer) serials.get(x)).getSerial_number()
												+ " no paso por HMU, FVS, P1_FVS o P1_HMU");
							} else {
								vHMU = false;
							}

						}

					} else {
						boolean vICT = false;
						boolean vHMUorFVS = false;
						List<String> serialNotProccess = new ArrayList();
						for (int x = 0; x < serials.size(); x++) {
							for (int y = 0; y < serialsProcessNames.size(); y++) {
								if (((SerialsOfContainer) serials.get(x)).getSerial_number()
										.equals(((ContainerSerial) serialsProcessNames.get(y)).getSerial_Number())) {
									if (((((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
											.equals("FVS"))
											&& (((ContainerSerial) serialsProcessNames.get(y)).getAction().trim()
													.equals("11")))

											|| ((((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
													.equals("P1_FVS"))
													&& (((ContainerSerial) serialsProcessNames.get(y)).getAction()
															.trim().equals("11")))

											|| ((((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
													.equals("HMU"))
													&& (((ContainerSerial) serialsProcessNames.get(y)).getAction()
															.trim().equals("11")))

											|| ((((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
													.equals("P1_HMU"))
													&& (((ContainerSerial) serialsProcessNames.get(y)).getAction()
															.trim().equals("11")))) {
										vHMUorFVS = true;
									}
									System.out.println("Process name del serial "
											+ ((ContainerSerial) serialsProcessNames.get(y)).getProcess_name()
											+ " Serial " + ((SerialsOfContainer) serials.get(x)).getSerial_number());
									if ((((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
											.equals("ICT"))
											&& (((ContainerSerial) serialsProcessNames.get(y)).getAction().trim()
													.equals("11"))

											|| (((ContainerSerial) serialsProcessNames.get(y)).getProcess_name().trim()
													.equals("P1_ICT"))
													&& (((ContainerSerial) serialsProcessNames.get(y)).getAction()
															.trim().equals("11"))

									) {
										vICT = true;
									}
								}
							}
							if (!vHMUorFVS) {
								serialNotProccess
										.add("el serial " + ((SerialsOfContainer) serials.get(x)).getSerial_number()
												+ " no paso por HMU, FVS, P1_FVS o P1_HMU");
							} else {
								vHMUorFVS = false;
							}
							if (!vICT) {
								serialNotProccess
										.add("el serial " + ((SerialsOfContainer) serials.get(x)).getSerial_number()
												+ " no paso por ICT o P1_ICT");
							} else {
								vICT = false;
							}
						}
						if (serialNotProccess.size() > 0) {
							for (int z = 0; z < serialNotProccess.size(); z++) {
								if (vMsg == "") {
									vMsg = "Serial error: " + (String) serialNotProccess.get(z);
								} else {
									vMsg = vMsg + ", " + (String) serialNotProccess.get(z);
								}
							}
							vMsg = vMsg + ", favor de validar.";
						} else {
							vMsg = "Ok";
						}
					}
				} else {
					vMsg = "Serial not found";
				}
			} else {
				Container cont = this.containerDao.findContainerByContainer(container.trim().toUpperCase());
				Ticket tempTicket = this.ticketDao.findTicketByIdTicket(Long.valueOf(cont.getIdTicket()));
				vMsg = "Ticket " + tempTicket.getTicket();
			}
			if (vMsg.equals("Ok")) {
				vMsg = "";
				String urlToJob = "http://localhost:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainer/"
						+ container.replace(" ", "%20").toUpperCase().trim();
				List<JSONObject> jsonSerials = new ArrayList();
				List<String> serialsShop = new ArrayList();
				jsonSerials = jsonViewList(urlToJob);
				for (int i = 0; i < jsonSerials.size(); i++) {
					System.out.println(((JSONObject) jsonSerials.get(i)).get("serial_number") + " "
							+ ((JSONObject) jsonSerials.get(i)).get("shop_order_key").toString());
					if (((JSONObject) jsonSerials.get(i)).get("shop_order_key").toString().contains("null")) {
						serialsShop.add(((JSONObject) jsonSerials.get(i)).get("serial_number").toString());
					}
				}
				if (!serialsShop.isEmpty()) {
					for (int y = 0; y < serialsShop.size(); y++) {
						if (vMsg.equals("")) {
							vMsg = "Seriales sin shop order " + (String) serialsShop.get(y);
						} else {
							vMsg = vMsg + ", " + (String) serialsShop.get(y);
						}
					}
				} else {
					vMsg = "Ok";
				}
			}
			System.out.println("Variable de retorno " + vMsg);

		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "coreCree", method = RequestMethod.POST)
	public @ResponseBody String coreCree(String container, String ticket, String project, String identifierPart) {
		String vMsg = "";
		try {
			String url;
			String partNumber;
			Ticket tk = ticketDao.findTicketByTicket(ticket);
			List<Container> listContainer = containerDao.findContainersByTicket(tk);
			if (listContainer.isEmpty()) {
				if (containerDao.findContainerByContainer(container.trim().toUpperCase()) == null) {
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ container.replace(" ", "%20").toUpperCase().trim();
					JSONObject jsonSerial = new JSONObject();
					if (jsonView(url) == null) {
						vMsg = "Serial not found";
					} else {
						jsonSerial = jsonView(url);
						JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
						if (jsonPart == null) {
							vMsg = "Este serial no tiene asignado un numero de parte";
						} else {
							partNumber = jsonPart.getString("partNumber");
							if (!partNumber.equals(identifierPart)) {
								vMsg = "El numero de parte del contenedor " + partNumber
										+ " es distinto al numero de parte escaneado de la etiqueta identificadora "
										+ identifierPart;
							} else if (project.equals("CREE CE") && !partNumber.substring(0, 6).contains("LFCRCE")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else if (project.equals("CREE CS") && !partNumber.substring(0, 6).contains("LFCRCS")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else if (project.equals("CREE UR") && !partNumber.substring(0, 6).contains("LFCRUR")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else if (project.equals("CREE CR6") && !partNumber.substring(0, 4).contains("LFCR")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else if (project.equals("CREE ZR") && !partNumber.toUpperCase().contains("ZR")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else if (project.equals("CREE PCA") && !partNumber.substring(0, 6).contains("LFCRLE")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else if (project.equals("CREE FLOURESCENT")
									&& !partNumber.substring(0, 5).contains("LFCRE")) {
								vMsg = "El numero de parte del serial no pertenece a la familia";
							} else {
								String url2 = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainer/"
										+ container.replace(" ", "%20").toUpperCase().trim();
								List<JSONObject> jsonSerials = new ArrayList<JSONObject>();
								List<String> serialsShop = new ArrayList<String>();
								jsonSerials = jsonViewList(url2);
								for (int i = 0; i < jsonSerials.size(); i++) {
									System.out.println(jsonSerials.get(i).get("serial_number") + " "
											+ jsonSerials.get(i).get("shop_order_key").toString());
									if (jsonSerials.get(i).get("shop_order_key").toString().contains("null")) {
										serialsShop.add(jsonSerials.get(i).get("serial_number").toString());
									}
								}
								if (serialsShop.isEmpty()) {
									vMsg = "Ok";
								} else {
									for (int y = 0; y < serialsShop.size(); y++) {
										if (vMsg.equals("")) {
											vMsg = "Seriales sin shop order " + serialsShop.get(y);
										} else {
											vMsg = vMsg + ", " + serialsShop.get(y);
										}
									}
								}
							}
						}
					}
				} else {
					Container cont = containerDao.findContainerByContainer(container);
					Ticket tempTicket = ticketDao.findTicketByIdTicket(cont.getIdTicket());
					vMsg = "Ticket " + tempTicket.getTicket();
				}
			} else {
				vMsg = "This ticket only allows one container";
			}
			System.out.println(vMsg);
		} catch (Exception e) {
			// TODO: handle exception
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "validateSerial", method = RequestMethod.POST)
	public @ResponseBody String validateSerial(String container, String partNumber, String project, String ticket,
			String poLine) {
		String vMsg = "";
		try {
			if (project.equals("NOKIA RRH")) {
				container = container.substring(3, container.length());
			} else if (project.contains("NOKIA FSM4")) {
				container = container.substring(2, container.length());
			}
			if (containerDao.findContainerByContainer(container) == null) {
				String url = "";
				if (project.contains("DIALIGHT")) {
					if (partNumber.contains("LFDGT")) {
						partNumber = partNumber.substring(6, partNumber.length());
					}
					// Si el serial es una RMA contara con algunos de estos prefijos asi que hay que
					// removerlos
					if (partNumber.contains("-SC")) {
						partNumber = partNumber.replace("-SC", "");
					} else if (partNumber.contains("-C")) {
						partNumber = partNumber.replace("-C", "");
					}
					if (project.contains("DIALIGHT PO ACCESORIOS")) {
						url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
								+ container.replace(" ", "%20").toUpperCase().trim();
					} else {
						url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
								+ partNumber + container.replace(" ", "%20").toUpperCase().trim();
					}
				} else if (project.equals("NOKIA FSM4")) {
					Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
					List<Container> listContainer = containerDao.findContainersByTicket(tempTicket);
					if (listContainer.size() > 0) {
						return "Ya no puedes agregar mas contenedores a este ticket";
					} else {
						url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
								+ container.replace(" ", "%20").toUpperCase().trim();
					}
				} else if (project.equals("NOKIA RRH")) {
					Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
					List<Container> listContainer = containerDao.findContainersByTicket(tempTicket);
					if (listContainer.size() > 6)
						return "Ya no puedes agregar mas seriales a este ticket";
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ container.replace(" ", "%20").toUpperCase().trim();
				} else {
					url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
							+ container.replace(" ", "%20").toUpperCase().trim();
				}
				JSONObject jsonSerial = new JSONObject();
				System.out.println(url);
				if (jsonView(url) == null) {
					vMsg = "Serial not found";
				} else {
					jsonSerial = jsonView(url);
					JSONObject jsonPart = (JSONObject) jsonSerial.get("partNumber");
					if (jsonPart == null) {
						vMsg = "Este serial no tiene asignado un numero de parte";
					} else {
						System.out.println("Validacion por el proyecto de las shop order: " + project);
						// Validacion de shop order por serial
						if (!project.equals("DIALIGHT PO ACCESORIOS")) {
							if (project.contains("DIALIGHT")) {// || project.contains("NOKIA RRH")) {
								if (jsonSerial.get("shopOrder").equals(null)) {
									return "Este serial no esta asignado a una shop order";
								}
							}
							// Validacion de shop order por contenedor o pallet
						}

						if (project.contains("IUSA") || project.contains("SIEMENS") || project.contains("HAAS")
								|| project.contains("ECHOSTAR") || project.contains("NOKIA FSM4")
								|| project.contains("VERIZON")) {
							// Validacion de shop order a nivel contenedor a ecepcion de CREE Check point
							System.out.println("Validacion de Shop Order " + project);
							if (project.contains("NOKIA FSM4"))
								container = container.substring(2, container.length());

							String urlToJob = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainer/"
									+ container.replace(" ", "%20").toUpperCase().trim();
							List<JSONObject> jsonSerials = new ArrayList<JSONObject>();
							List<String> serialsShop = new ArrayList<String>();
							jsonSerials = jsonViewList(urlToJob);
							for (int i = 0; i < jsonSerials.size(); i++) {
								System.out.println(jsonSerials.get(i).get("serial_number") + " "
										+ jsonSerials.get(i).get("shop_order_key").toString());
								if (jsonSerials.get(i).get("shop_order_key").toString().contains("null")) {
									serialsShop.add(jsonSerials.get(i).get("serial_number").toString());
								}
							}
							if (!serialsShop.isEmpty()) {
								for (int y = 0; y < serialsShop.size(); y++) {
									if (vMsg.equals("")) {
										vMsg = "Seriales sin shop order " + serialsShop.get(y);
									} else {
										vMsg = vMsg + ", " + serialsShop.get(y);
									}
								}
							}

						}
						if (vMsg.equals("")) {
							if (!project.contains("NOKIA FSM4") && !project.equals("COLOR KINETICS TELETROL")
									&& !project.equals("COLOR KINETICS CAPTIVATION") && !project.contains("SIEMENS")
									&& !project.contains("DIGITAL LUMENS CONTAINER") && !project.contains("HAAS")
									&& !project.contains("DIALIGHT PO ACCESORIOS")
									&& !project.contains("COLOR KINETICS PCA")) {
								JSONObject jsonLocation = (JSONObject) jsonSerial.get("location");
								if (jsonLocation == null) {
									vMsg = "Este serial no tiene asignada una ubicacion";
								} else {
									if (jsonLocation.getString("shortWorkStation").equals("COMP")) {
										vMsg = "Ok";
									} else {
										vMsg = "El serial no se encuentra en complete";
									}
								}
							} else {
								vMsg = "Ok";
							}
						}
					}
				}
			} else {
				Container cont = containerDao.findContainerByContainer(container);
				Ticket tempTicket = ticketDao.findTicketByIdTicket(cont.getIdTicket());
				vMsg = "Ticket " + tempTicket.getTicket();
			}
		} catch (Exception e) {
			vMsg = "Error: " + e.getMessage();
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "saveTicket", method = RequestMethod.POST)
	public @ResponseBody String saveTicket(String project, String ticket, String poLine, Integer qty, String partNumber,
			HttpServletRequest request) {
		String vMsg = "";
		try {
			// Guardado de ticket por Po Line
			if (project.contains("DIALIGHT") || project.contains("SIEMENS")) {
				Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
				List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
				int tempQtyStored = 0;
				for (int i = 0; i < containerList.size(); i++) {
					Container tempContainer = containerDao
							.findContainerByContainer(containerList.get(i).getContainer());
					tempContainer.setStatus(1);
					containerDao.updateContainer(tempContainer);
					tempQtyStored = (int) tempContainer.getQty();
				}
				tempTicket.setEnabled("1");
				tempTicket.setQtyContainers(containerList.size());
				tempTicket.setReleasedBy(request.getSession().getAttribute("LOGGEDIN_USER").toString());
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = new Date();
				String formattedDate = formatter.format(date);
				tempTicket.setDte(formatter.parse(formattedDate));
				Project tempProject = projectDao.findProjectByProject(project);
				tempTicket.setProjectId(tempProject.getIdProject());
				ticketDao.updateTicket(tempTicket);
				vMsg = "Ok";
			} else {
				if (project.contains("ECHOSTAR")) {
					Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
					Container tempcontainer = containerDao.findContainerByIdTicket(tempTicket.getIdTicket());
					String partsCountOfPallet = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/DistinctPartCountByContainer/"
							+ tempcontainer.getContainer().trim().toUpperCase();

					List<JSONObject> distinctPartsCount = (List<JSONObject>) jsonViewList(partsCountOfPallet);
					if (!distinctPartsCount.get(0).isNull("partNumber")) {
						List<Container> containerList = containerDao.findContainerByLike(tempcontainer.getContainer());
						Project tempProject = projectDao.findProjectByProject(project);
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = new Date();
						String formattedDate = formatter.format(date);
						for (int i = 0; i < containerList.size(); i++) {
							tempTicket = ticketDao.findTicketByIdTicket(containerList.get(i).getIdTicket());
							tempTicket.setDte(formatter.parse(formattedDate));
							tempTicket.setProjectId(tempProject.getIdProject());
							tempTicket.setReleasedBy(request.getSession().getAttribute("LOGGEDIN_USER").toString());
							ticketDao.updateTicket(tempTicket);
						}
						vMsg = "Ok";
					} else {
						List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
						for (int i = 0; i < containerList.size(); i++) {
							Container tempContainer = containerDao
									.findContainerByContainer(containerList.get(i).getContainer());
							tempContainer.setStatus(1);
							containerDao.updateContainer(tempContainer);
						}
						tempTicket.setEnabled("1");
						tempTicket.setQtyContainers(containerList.size());
						tempTicket.setReleasedBy(request.getSession().getAttribute("LOGGEDIN_USER").toString());
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date date = new Date();
						String formattedDate = formatter.format(date);
						tempTicket.setDte(formatter.parse(formattedDate));
						Project tempProject = projectDao.findProjectByProject(project);
						tempTicket.setProjectId(tempProject.getIdProject());
						ticketDao.updateTicket(tempTicket);
						vMsg = "Ok";
					}

				} else {
					Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
					List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
					for (int i = 0; i < containerList.size(); i++) {
						Container tempContainer = containerDao
								.findContainerByContainer(containerList.get(i).getContainer());
						tempContainer.setStatus(1);
						containerDao.updateContainer(tempContainer);
					}
					tempTicket.setEnabled("1");
					tempTicket.setQtyContainers(containerList.size());
					tempTicket.setReleasedBy(request.getSession().getAttribute("LOGGEDIN_USER").toString());
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date date = new Date();
					String formattedDate = formatter.format(date);
					tempTicket.setDte(formatter.parse(formattedDate));
					Project tempProject = projectDao.findProjectByProject(project);
					tempTicket.setProjectId(tempProject.getIdProject());
					ticketDao.updateTicket(tempTicket);
					vMsg = "Ok";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "printTicket", method = RequestMethod.POST)
	public @ResponseBody String printTicket(String ticket, String project, String poLine) {
		String vMsg = "";
		try {
			Ticket tk = ticketDao.findTicketByTicket(ticket);
			List<Container> containers = containerDao.findContainersByTicket(tk);
			System.out.println("Proyecto " + project);
			Project tempProject = projectDao.findProjectByProject(project);
			PrintZPL stateZPL = new PrintZPL(zpldao);
			if (project.contains("DIALIGHT")) {
				String partNumber = containers.get(0).getPartNumber();
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				vMsg = stateZPL.printDIALIGHT(ticket, containers, tempProject.getIdProject(), partNumber);
				System.out.println("Valor del mensaje de impresion " + vMsg);
			} else if (project.contains("NOKIA FSM4")) {
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containers.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String revision = jPart.getString("revision");
				String partNumber = jPart.getString("partNumber");
				int qty = jSerial.getInt("qty");
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				vMsg = stateZPL.printNOKIAFSM4(tk.getTicket(), partNumber, revision, qty, tempProject.getIdProject());
			} else if (project.equals("NOKIA RRH")) {
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containers.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String revision = jPart.getString("revision");
				String partNumber = jPart.getString("partNumber");
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				vMsg = stateZPL.printNOKIARRH(ticket, containers, tempProject.getIdProject(), project, revision,
						partNumber);
			} else if (project.contains("IUSA")) {
				String partIUSA = containers.get(0).getPartNumber();
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Part/PartNumber/FindDescriptionByPartNumber/"
						+ partIUSA;
				String description = "";
				if (jsonViewString(url) != null) {
					description = jsonViewString(url);
				}
				long qtyIUSA = 0;
				for (int i = 0; i < containers.size(); i++) {
					qtyIUSA = qtyIUSA + containers.get(i).getQty();
				}
				vMsg = stateZPL.printIUSA(ticket, tempProject.getIdProject(), partIUSA, qtyIUSA, description);
			} else if (project.contains("COLOR KINETICS")) {
				vMsg = stateZPL.printColor(ticket, containers, tempProject.getIdProject(),
						tempProject.getProjectName());
			} else if (project.contains("DIGITAL LUMENS")) {
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containers.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String modelDescription = jPart.getString("modelDescription");
				vMsg = stateZPL.printDigital(ticket, containers, tempProject.getIdProject(),
						tempProject.getProjectName(), modelDescription);
			} else if (project.contains("HAAS")) {
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containers.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String partNumber = jPart.getString("partNumber");
				if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
					MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
					partNumber = mapped.getPartNumberCustomer();
				}
				String modelDescription = jPart.getString("description");
				vMsg = stateZPL.printHAAS(ticket, containers, tempProject.getIdProject(), tempProject.getProjectName(),
						modelDescription, partNumber);
			} else if (project.contains("SIEMENS")) {
				String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialBySerial/"
						+ containers.get(0).getContainer().replace(" ", "%20").toUpperCase().trim();
				JSONObject jSerial = jsonView(url);
				JSONObject jPart = (JSONObject) jSerial.get("partNumber");
				String modelDescription = jPart.getString("description");
				vMsg = stateZPL.printSIEMENS(ticket, containers, tempProject.getIdProject(),
						tempProject.getProjectName(), modelDescription);
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "buildPDFECHO")
	public ResponseEntity<byte[]> buildPDFECHO(String project, String ticket) {
		byte[] contents;
		ResponseEntity<byte[]> response = null;
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			List<Container> tempContainer = containerDao.findContainersByTicket(tempTicket);
			String partNumber = tempContainer.get(0).getPartNumber();
			String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Part/PartNumber/FindDescriptionByPartNumber/"
					+ partNumber.trim().toUpperCase();
			String decription = jsonViewString(url);
			if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
				MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
				partNumber = mapped.getPartNumberCustomer();
			}

			long qtyPrint = 0;
			for (int i = 0; i < tempContainer.size(); i++) {
				qtyPrint = qtyPrint + tempContainer.get(i).getQty();
			}

			if (partNumber.contains("LFDH-212160")) {
				qtyPrint = 0;
				String urlBoxes = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/FindSerialsByContainerGeneral/"
						+ tempContainer.get(0).getContainer().trim().toUpperCase();
				List<JSONObject> boxes = (List<JSONObject>) jsonViewList(urlBoxes);
				List<ContainersOfSkid> LstContainers = new ArrayList<ContainersOfSkid>();
				if (jsonViewList(urlBoxes) != null) {
					for (int x = 0; x < boxes.size(); x++) {
						int id = (Integer) boxes.get(x).get("id");
						int qty = (Integer) boxes.get(x).get("quantity");
						String jPartNumber = boxes.get(x).get("partNumber").toString();
						String serialNumber = boxes.get(x).get("serialNumber").toString();
						ContainersOfSkid cont = new ContainersOfSkid();
						cont.setID(id);
						cont.setPartNumber(jPartNumber);
						cont.setQuantity(qty);
						cont.setSerialNumber(serialNumber);
						LstContainers.add(cont);
					}
				}
				for (int y = 0; y < LstContainers.size(); y++) {
					System.out.println("cantidad de la box " + y + ": " + LstContainers.get(y).getQuantity());
					qtyPrint = qtyPrint + LstContainers.get(y).getQuantity();
				}
			}

			// Creaciion del pdf
			String partsCountOfPallet = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Serial/Serial/DistinctPartCountByContainer/"
					+ tempContainer.get(0).getContainer().trim().toUpperCase();

			List<JSONObject> distinctPartsCount = jsonViewList(partsCountOfPallet);

			// Revisamos si el pallet tine mas de un numero de parte para validar si es un
			// parcial e imprimir la n cantidad de tickets igual a la n cantidad de los
			// numeros de parte
			if (distinctPartsCount.size() > 1) {
				List<Container> containers = new ArrayList<Container>();
				List<TicketPartial> partialList = new ArrayList<TicketPartial>();
				if (!containerDao.findContainerByLike(tempContainer.get(0).getContainer()).isEmpty()) {
					containers = containerDao.findContainerByLike(tempContainer.get(0).getContainer());
				}
				Ticket tk;
				url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Part/PartNumber/FindDescriptionByPartNumber/"
						+ containers.get(0).getPartNumber().trim().toUpperCase();
				decription = jsonViewString(url);
				for (int i = 0; i < containers.size(); i++) {
					TicketPartial partial = new TicketPartial();
					tk = ticketDao.findTicketByIdTicket(containers.get(i).getIdTicket());
					partial.setPallet(containers.get(i).getContainer());
					partial.setPartNumber(containers.get(i).getPartNumber());
					partial.setQty((int) containers.get(i).getQty());
					partial.setTicket(tk.getTicket());
					if (decription != null) {
						partial.setDescription(decription);
					}
					partialList.add(partial);
				}
				String path = "ticketC.pdf";
				File file = new File(path);
				BuildPdfDishPartial doc = new BuildPdfDishPartial(partialList);
				Document document = new Document(PageSize.A4);
				System.out.println("Document created...");
				try {
					PdfWriter.getInstance(document, new FileOutputStream(file));
					System.out.println("Writer instance created...");
					document.open();
					System.out.println("Document open...");
					doc.buildDoc(document);
					System.out.println("Final format to document...");
				} catch (Exception e) {
					e.printStackTrace();
				}
				document.close();
				System.out.println("Document closed...");
				contents = loadFile(path);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType("application/pdf"));
				String filename = "Ticket_Partial_Dish_" + tempContainer.get(0).getContainer() + ".pdf";
				headers.setContentDispositionFormData(filename, filename);
				headers.setExpires(0);
				headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				headers.setPragma("public");
				response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
				// return response;
			} else {
				String path = "ticketC.pdf";
				File file = new File(path);
				BuildPdfEcho doc = new BuildPdfEcho(partNumber, ticket, project, qtyPrint, decription,
						tempContainer.get(0).getContainer().trim().toUpperCase());
				Document document = new Document(PageSize.A4);
				System.out.println("Document created...");
				try {
					PdfWriter.getInstance(document, new FileOutputStream(file));
					System.out.println("Writer instance created...");
					document.open();
					System.out.println("Document open...");
					doc.buildDoc(document);
					System.out.println("Final format to document...");
				} catch (Exception e) {
					e.printStackTrace();
				}
				document.close();
				System.out.println("Document closed...");
				contents = loadFile(path);
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType("application/pdf"));
				String filename = "Ticket: " + ticket + "_Dish" + ".pdf";
				headers.setContentDispositionFormData(filename, filename);
				headers.setExpires(0);
				headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
				headers.setPragma("public");
				response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "buildPDFCEE")
	public ResponseEntity<byte[]> buildPDFCEE(String project, String ticket) {
		byte[] contents;
		ResponseEntity<byte[]> response = null;
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			List<Container> tempContainer = containerDao.findContainersByTicket(tempTicket);
			String partNumber = tempContainer.get(0).getPartNumber();
			String url = "http://gdl1amwebw03.am.sanm.corp:8080/WebServiceCirrus/Part/PartNumber/FindDescriptionByPartNumber/"
					+ partNumber.trim().toUpperCase();
			String decription = jsonViewString(url);
			if (mappedDao.findMappedByPartSanminaActive(partNumber) != null) {
				MappedPartNumber mapped = mappedDao.findMappedByPartSanminaActive(partNumber);
				partNumber = mapped.getPartNumberCustomer();
			}
			long qtyPrint = 0;
			for (int i = 0; i < tempContainer.size(); i++) {
				qtyPrint = qtyPrint + tempContainer.get(i).getQty();
			}
			String path = "ticketC.pdf";
			File file = new File(path);
			BuildPdfCREE doc = new BuildPdfCREE(partNumber, ticket, project, qtyPrint, decription,
					tempContainer.get(0).getContainer().trim().toUpperCase());
			Document document = new Document(PageSize.A4);
			System.out.println("Document created...");
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				System.out.println("Writer instance created...");
				document.open();
				System.out.println("Document open...");
				doc.buildDoc(document);
				System.out.println("Final format to document...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
			System.out.println("Document closed...");
			contents = loadFile(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "Ticket: " + ticket + "_" + project + ".pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setExpires(0);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setPragma("public");
			response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "buildPDFColor")
	public ResponseEntity<byte[]> buildPDFColor(String project, String ticket) {
		byte[] contents;
		ResponseEntity<byte[]> response = null;
		try {
			Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
			List<Container> containers = containerDao.findContainersByTicket(tempTicket);
			String path = "ticketC.pdf";
			File file = new File(path);
			BuildPdfCOLOR doc = new BuildPdfCOLOR(containers, ticket, project);
			Document document = new Document(PageSize.A4);
			System.out.println("Document created...");
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				System.out.println("Writer instance created...");
				document.open();
				System.out.println("Document open...");
				doc.buildDoc(document);
				System.out.println("Final format to document...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
			System.out.println("Document closed...");
			contents = loadFile(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "Ticket: " + ticket + "_" + project + ".pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setExpires(0);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setPragma("public");
			response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "buildPDF")
	public ResponseEntity<byte[]> buildPDF(String project, String ticket) {
		byte[] contents;
		ResponseEntity<byte[]> response = null;
		Ticket tempTicket = ticketDao.findTicketByTicket(ticket);
		List<Container> tempContainer = containerDao.findContainersByTicket(tempTicket);
		Long containerskid = Long.parseLong(tempContainer.get(0).getContainer());
		List<SerialSkid> serialSkid = serialSkidDao.findSerialByContainer(containerskid);
		Long idPart = serialSkid.get(0).getIdPartNumber();
		PartNumberSkid partPrint = partNumberSkidDao.findPartByIdPart(idPart);
		long qtyPrint = 0;
		for (int i = 0; i < tempContainer.size(); i++) {
			qtyPrint = qtyPrint + tempContainer.get(i).getQty();
		}
		MappedPartNumber partNumber = new MappedPartNumber();
		String part = "";
		if (mappedDao.findMappedByPartSanminaActive(partPrint.getPartNumber()) != null) {
			partNumber = mappedDao.findMappedByPartSanminaActive(partPrint.getPartNumber());
			part = partNumber.getPartNumberCustomer();
		} else {
			part = partPrint.getPartNumber();
		}
		try {
			String path = "ticketC.pdf";
			File file = new File(path);
			BuildPdf doc = new BuildPdf(part, ticket, project, qtyPrint);
			Document document = new Document(PageSize.A4);
			System.out.println("Document created...");
			try {
				PdfWriter.getInstance(document, new FileOutputStream(file));
				System.out.println("Writer instance created...");
				document.open();
				System.out.println("Document open...");
				doc.buildDoc(document);
				System.out.println("Final format to document...");
			} catch (Exception e) {
				e.printStackTrace();
			}
			document.close();
			System.out.println("Document closed...");
			contents = loadFile(path);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/pdf"));
			String filename = "Ticket: " + ticket + "_" + project + ".pdf";
			headers.setContentDispositionFormData(filename, filename);
			headers.setExpires(0);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			headers.setPragma("public");
			response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	@RequestMapping(value = "getProjects")
	public @ResponseBody List<Project> getProjects() {
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

	public String jsonViewString(String url) throws IOException {
		String vMsg = "";
		try {
			URL TempUrl = new URL(url);
			URLConnection urlConnection = TempUrl.openConnection();
			urlConnection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null)
				vMsg = inputLine;
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMsg;
	}

	public List<String> jsonViewListString(String url) throws IOException {
		List<String> vMsg = new ArrayList<String>();
		try {
			URL TempUrl = new URL(url);
			URLConnection urlConnection = TempUrl.openConnection();
			urlConnection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null)
				vMsg.add(inputLine);
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMsg;
	}

	public List<JSONObject> jsonViewList(String url) throws IOException {
		List<JSONObject> jsonobjectlst = new ArrayList<JSONObject>();
		try {
			JSONObject jsonobject = null;
			URL TempUrl = new URL(url);
			URLConnection urlConnection = TempUrl.openConnection();
			urlConnection.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			String inputLine;
			JSONArray jsonarray = null;
			while ((inputLine = br.readLine()) != null)
				jsonarray = new JSONArray(inputLine);
			br.close();
			if (jsonarray.length() != 0)
				for (int i = 0; i < jsonarray.length(); i++) {
					jsonobject = jsonarray.getJSONObject(i);
					jsonobjectlst.add(jsonobject);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonobjectlst;
	}

	public static byte[] readFully(InputStream stream) throws IOException {
		byte[] buffer = new byte[8192];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int bytesRead;
		while ((bytesRead = stream.read(buffer)) != -1)
			baos.write(buffer, 0, bytesRead);
		return baos.toByteArray();
	}

	public static byte[] loadFile(String sourcePath) throws IOException {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(sourcePath);
			return readFully(inputStream);
		} finally {
			if (inputStream != null)
				inputStream.close();
		}
	}
}
