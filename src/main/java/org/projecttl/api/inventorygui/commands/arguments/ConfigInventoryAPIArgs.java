package org.projecttl.api.inventorygui.commands.arguments;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ConfigInventoryAPIArgs implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> firstArgument = new ArrayList<>();
            firstArgument.add("development");
            firstArgument.add("test");

            return firstArgument;
        }

        if (args.length == 2) {
            ArrayList<String> secondArgument = new ArrayList<>();

            if (args[0].equalsIgnoreCase("development")) {
                secondArgument.add("info");

                return secondArgument;
            }

            else if (args[0].equalsIgnoreCase("test")) {
                secondArgument.add("test");

                return secondArgument;
            }
        }

        if (args.length == 3) {
            ArrayList<String> thirdArgument = new ArrayList<>();

            if (args[0].equalsIgnoreCase("development")) {
                if (args[1].equalsIgnoreCase("info")) {
                    thirdArgument.add("another");
                    thirdArgument.add("developer");
                    thirdArgument.add("github");
                    thirdArgument.add("language");

                    return thirdArgument;
                }
            }
        }

        return onTabComplete(sender, command, alias, args);
    }
}
