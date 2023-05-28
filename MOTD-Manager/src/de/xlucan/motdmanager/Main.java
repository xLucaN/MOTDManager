package de.xlucan.motdmanager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public static String prefix = "§c§lMOTDMANAGER §8» §7";

	public void onEnable() {
		loadConfig();
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
		System.out.println("§c§lMOTDMANAGER §8» §aDas Plugin wurde erwolgreich Geladen.");
		System.out.println("§c§lMOTDMANAGER §8» §aby xLucaN");
	}

	@EventHandler
	public void ServerListPing(ServerListPingEvent e) {
		String MOTD = getConfig().getString("Config.MOTD");
		String MOTD2 = getConfig().getString("Config.MOTD2");
		e.setMotd(ChatColor.translateAlternateColorCodes('&', String.valueOf(MOTD) + "\n" + MOTD2));
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (p.hasPermission("motdmanager.motd") && label.equalsIgnoreCase("motd")) {
			if (args.length == 0) {
				p.sendMessage("§8§l§m-------------[--§7 §c§lMOTDMANAGER §8§l§m--]-------------");
				p.sendMessage("§8» §c/motd 1 <text> §8| §7änder die erste Reihe der MOTD!");
				p.sendMessage("§8» §c/motd 2 <text> §8| §7änder die zweite Reihe der MOTD!");
				p.sendMessage("§8» §cPlugin von: §7xLucaN §8| §cVersion: §71.0");
				p.sendMessage("§8» §cPermission: §7motdmanager.motd");
				p.sendMessage("§8§l§m-------------[--§7 §c§lMOTDMANAGER §8§l§m--]-------------");
			}
			if (args.length >= 2) {
				if (args[0].equalsIgnoreCase("1")) {
					String motd = "";
					for (int i = 1; i < args.length; i++) {
						motd = String.valueOf(motd) + " " + args[i];
					}
					getConfig().set("Config.MOTD", motd);
					saveConfig();
					p.sendMessage(prefix + "Die 1. Reihe MOTD wurde erfolgreich geändert!");
					p.sendMessage(prefix + motd);
				} else if (args.length >= 2 && args[0].equalsIgnoreCase("2")) {
					String motd = "";
					for (int i = 1; i < args.length; i++) {
						motd = String.valueOf(motd) + " " + args[i];
					}
					getConfig().set("Config.MOTD2", motd);
					saveConfig();
					p.sendMessage(prefix + "Die 2. Reihe MOTD wurde erfolgreich geändert!");
					p.sendMessage(prefix + motd);
				}
			}
		}

		return false;
	}

	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}