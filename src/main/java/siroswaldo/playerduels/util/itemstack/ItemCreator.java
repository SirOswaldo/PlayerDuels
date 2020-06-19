package siroswaldo.playerduels.util.itemstack;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

public class ItemCreator {

    private ItemStack item;

    public ItemCreator(Material material, byte data){
        item = new ItemStack(material, 1,(short ) 1, data);
    }
    public ItemCreator(Material material){
        item = new ItemStack(material);
    }
    public ItemCreator(){
        item = new ItemStack(Material.STONE);
    }
    public ItemStack getItem() { return new ItemStack(item); }

    public Material getMaterial(){
        return item.getType();
    }
    public void setMaterial(Material material){
        item.setType(material);
    }

    public short getDurability(){
        return item.getDurability();
    }
    public void setDurability(short durability){
        item.setDurability(durability);
    }

    public short getData(){
        MaterialData materialData = item.getData();
        return materialData.getData();
    }
    public void setData(byte data){
        MaterialData materialData = item.getData();
        materialData.setData(data);
        item.setData(materialData);
    }

    public int getAmount(){
        return item.getAmount();
    }
    public void setAmount(int amount){
        if (amount > 64){
            item.setAmount(64);
        } else if  (amount < 1){
            item.setAmount(1);
        } else {
            item.setAmount(amount);
        }
    }

    public void setDisplayName(String name, boolean color){
        ItemMeta meta = item.getItemMeta();
        if (color){
            name = ChatColor.translateAlternateColorCodes('&', name);
        }
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }
    public String  getDisplayName(){
        ItemMeta meta = item.getItemMeta();
        if (meta.hasDisplayName()){
            return meta.getDisplayName();
        }
        return "";
    }

    public void setLore(List<String> lore, boolean color){
        ItemMeta meta = item.getItemMeta();
        if (color){
            for (int i = 0;i<lore.size();i++){
                lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
            }
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
    }
    public List<String> getLore(){
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()) {
            return meta.getLore();
        }
        return new ArrayList<>();
    }
    public void addLoreLine(String line, boolean color){
        if (color){
            line = ChatColor.translateAlternateColorCodes('&', line);
        }
        ItemMeta meta = item.getItemMeta();
        if (meta.hasLore()){
            List<String> lore = meta.getLore();
            lore.add(line);
            meta.setLore(lore);
        } else {
            List<String> lore = new ArrayList<>();
            lore.add(line);
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
    }

}
