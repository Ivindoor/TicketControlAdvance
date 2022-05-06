package com.sanmina.tk.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanmina.tk.orm.dao.ContainerDao;
import com.sanmina.tk.orm.dao.ProjectDao;
import com.sanmina.tk.orm.dao.TicketDao;
import com.sanmina.tk.orm.dao.UserDao;
import com.sanmina.tk.orm.dao.tbl_TicketDeletedDao;
import com.sanmina.tk.orm.model.Ticket;
import com.sanmina.tk.orm.model.TicketForEliminate;
import com.sanmina.tk.orm.model.Users;
import com.sanmina.tk.orm.model.tbl_TicketDeleted;
import com.sanmina.tk.orm.model.Container;

@Controller
public class RequestSearch {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private ContainerDao containerDao;
	@Autowired
	private tbl_TicketDeletedDao ticketDeletedDao;

	@RequestMapping(value = "BuscarTicket")
	public ModelAndView landingResolv(ModelMap model) {
		return new ModelAndView("BuscarTicket/BuscarTicket", model);
	}

	@RequestMapping(value = "cleanTicket")
	public ModelAndView CleanTicket(ModelMap model) {
		return new ModelAndView("BuscarTicket/TableDiv", model);
	}

	@RequestMapping(value = "enabledDelete")
	public @ResponseBody Integer EabledDelete(HttpServletRequest request) {
		int vMsg = 0;
		try {
			Users user = userDao.findUserByUserLogin(request.getSession().getAttribute("LOGGEDIN_USER").toString());
			vMsg = user.getLevelTicket();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vMsg;
	}

	@RequestMapping(value = "removeSerial")
	public @ResponseBody String removeSerial(String serial, HttpServletRequest request, String comment) {
		String vMsg = "";
		try {
			Container container = containerDao.findContainerByContainer(serial);
			Ticket ticket = ticketDao.findTicketByIdTicket(container.getIdTicket());
			List<Container> listContainer = containerDao.findContainersByTicket(ticket);
			tbl_TicketDeleted ticketDeleted = new tbl_TicketDeleted();
			ticketDeleted.setDteDeleted(new Date());
			ticketDeleted.setTicket(ticket.getTicket());
			ticketDeleted.setId_ticket(ticket.getIdTicket());
			ticketDeleted.setIdProject(ticket.getProjectId());
			ticketDeleted.setUserName(request.getSession().getAttribute("LOGGEDIN_USER").toString());
			ticketDeleted.setSerials(serial);
			ticketDeleted.setDescription(comment);
			ticketDeletedDao.saveTbl_TicketDeleted(ticketDeleted);
			containerDao.deleteContainer(container);
			List<Container> currentContainers = containerDao.findContainersByTicket(ticket);
			if (currentContainers.size() == 0 || currentContainers == null) {
				ticketDao.deleteTicketByTicket(ticket);
			} else {
				ticket.setQtyContainers(listContainer.size() - 1);
				ticketDao.updateTicket(ticket);
			}
			System.out.println(vMsg);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vMsg = e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "compareUser")
	public @ResponseBody TicketForEliminate compareUser(HttpServletRequest request, String ticketOrSerial) {
		TicketForEliminate vUser = new TicketForEliminate();
		try {
			Users user = userDao.findUserByUserLogin(request.getSession().getAttribute("LOGGEDIN_USER").toString());

			Ticket ticket = new Ticket();

			if (ticketDao.findTicketByTicket(ticketOrSerial) != null) {
				ticket = ticketDao.findTicketByTicket(ticketOrSerial);
			} else {
				Container container = containerDao.findContainerByContainer(ticketOrSerial);
				ticket = ticketDao.findTicketByIdTicket(container.getIdTicket());
			}

			vUser.setDepartment(user.getDepartamento());
			vUser.setUsrName(request.getSession().getAttribute("LOGGEDIN_USER").toString());
			vUser.setEnabled(Integer.parseInt(ticket.getEnabled()));
			vUser.setTicketOrSerial(ticketOrSerial);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vUser;
	}

	@RequestMapping(value = "deleteByTicket", method = RequestMethod.POST)
	public @ResponseBody String DeleteByTicket(String value, String comment, HttpServletRequest request) {
		String vMsg = "";
		try {
			Users user = userDao.findUserByUserLogin(request.getSession().getAttribute("LOGGEDIN_USER").toString());

			Ticket ticket = new Ticket();
			if (ticketDao.findTicketByTicket(value) == null) {
				System.out.println("Ticket don't exist");
				vMsg = "Ticket don't exist";
			} else {
				ticket = ticketDao.findTicketByTicket(value);

				if (user.getDepartamento() != "IT" && Integer.parseInt(ticket.getEnabled()) > 1) {

				}
				ticket = ticketDao.findTicketByTicket(value);
				tbl_TicketDeleted ticketDeleted = new tbl_TicketDeleted();
				ticketDeleted.setTicket(ticket.getTicket());
				ticketDeleted.setId_ticket(ticket.getIdTicket());
				ticketDeleted.setIdProject(ticket.getProjectId());
				ticketDeleted.setDescription(comment);
				ticketDeleted.setUserName(request.getSession().getAttribute("LOGGEDIN_USER").toString());
				ticketDeleted.setDteDeleted(new Date());
				ticketDeletedDao.saveTbl_TicketDeleted(ticketDeleted);
				// Eliminar el ticket y los seriales
				ticketDao.deleteTicketByTicket(ticket);
				if (ticketDao.findTicketByTicket(value) == null) {
					vMsg = "Ticket has deleted";
					System.out.println("Ticket has deleted");
				}
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "deleteBySerial", method = RequestMethod.POST)
	public @ResponseBody String DeleteBySerial(String value, String comment, HttpServletRequest request) {
		String vMsg = "";
		try {
			Container container = new Container();
			if (containerDao.findContainerByContainer(value) == null) {
				vMsg = "Serial don't exist";
			} else {
				container = containerDao.findContainerByContainer(value);
				Ticket ticket = new Ticket();
				if (ticketDao.findTicketByIdTicket(container.getIdTicket()) == null) {
					vMsg = "Ticket don't exist";
				} else {
					ticket = ticketDao.findTicketByIdTicket(container.getIdTicket());
					if (projectDao.findProjectByIdProject(ticket.getProjectId()) == null) {
						vMsg = "The ticket has not been assigned to a project";
					} else {
						tbl_TicketDeleted ticketDeleted = new tbl_TicketDeleted();
						ticketDeleted.setTicket(ticket.getTicket());
						ticketDeleted.setId_ticket(ticket.getIdTicket());
						ticketDeleted.setIdProject(ticket.getProjectId());
						ticketDeleted.setDescription(comment);
						ticketDeleted.setUserName(request.getSession().getAttribute("LOGGEDIN_USER").toString());
						ticketDeleted.setDteDeleted(new Date());
						ticketDeletedDao.saveTbl_TicketDeleted(ticketDeleted);
						// Eliminar el ticket y los seriales
						ticketDao.deleteTicketByTicket(ticket);
						if (ticketDao.findTicketByTicket(value) == null) {
							vMsg = "Ticket has deleted";
							System.out.println("Ticket has deleted");
						}
						// }
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "searchByTicket")
	public @ResponseBody String searchByTicket(String value) {
		String vMsg = "";
		try {
			Ticket tempTicket = new Ticket();
			if (ticketDao.findTicketByTicket(value) == null) {
				vMsg = "Ticket don't exist";
			} else {
				tempTicket = ticketDao.findTicketByTicket(value);
				if (containerDao.findContainersByTicket(tempTicket) == null) {
					vMsg = "Serials don't exist";
				} else {
					vMsg = "Ok";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "searchBySerial")
	public @ResponseBody String SearchBySerial(String value) {
		String vMsg = "";
		try {
			if (containerDao.findContainerByContainer(value) == null) {
				vMsg = "Serial don't exist";
			} else {
				Ticket tempTicket = new Ticket();
				Container container = containerDao.findContainerByContainer(value);
				if (ticketDao.findTicketByIdTicket(container.getIdTicket()) == null) {
					vMsg = "Ticket don't exist";
				} else {
					tempTicket = ticketDao.findTicketByIdTicket(container.getIdTicket());
					List<Container> containerList = containerDao.findContainersByTicket(tempTicket);
					if (containerList == null) {
						vMsg = "Serials don't exist";
					} else {
						vMsg = "Ok";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			vMsg = "Error: " + e.getMessage();
		}
		return vMsg;
	}

	@RequestMapping(value = "loadInfoBySerial", method = RequestMethod.POST)
	public @ResponseBody ModelAndView LoadInfoBySerial(ModelMap model, String value) {
		try {
			Container findContainer = containerDao.findContainerByContainer(value);
			Ticket ticket = ticketDao.findTicketByIdTicket(findContainer.getIdTicket());
			List<Container> containerList = containerDao.findContainersByTicket(ticket);
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
			model.addAttribute("containerList", containerList);
			return new ModelAndView("BuscarTicket/TableDiv", model);
		} catch (NullPointerException ex) {
			return new ModelAndView("home/notFound", model);
		}
	}

	@RequestMapping(value = "loadInfoByTicket", method = RequestMethod.POST)
	public @ResponseBody ModelAndView LoadInfoByTicket(ModelMap model, String value) {
		List<Container> containerList = new ArrayList<Container>();
		Ticket ticket = ticketDao.findTicketByTicket(value);
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
			containerList = containerDao.findContainersByTicket(ticket);
			model.addAttribute("containerList", containerList);
			model.addAttribute("totalContainers", containerList.size());
			System.out.println(ticket.getTicket() + " " + containerList.get(0).getContainer());
			return new ModelAndView("BuscarTicket/TableDiv", model);
		} catch (NullPointerException e) {
			return new ModelAndView("home/notFound", model);
		}
	}
}
