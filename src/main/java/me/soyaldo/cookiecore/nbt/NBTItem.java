package me.soyaldo.cookiecore.nbt;

import lombok.Getter;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class NBTItem {

    private final JavaPlugin javaPlugin;
    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private final PersistentDataContainer persistentDataContainer;

    public NBTItem(JavaPlugin javaPlugin, ItemStack itemStack) {
        this.javaPlugin = javaPlugin;
        this.itemStack = itemStack;
        if (itemStack != null) {
            itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                persistentDataContainer = itemMeta.getPersistentDataContainer();
            } else {
                persistentDataContainer = null;
            }
        } else {
            itemMeta = null;
            persistentDataContainer = null;
        }
    }

    public NBTItem(ItemStack itemStack) {
        this.javaPlugin = null;
        this.itemStack = itemStack;
        if (itemStack != null) {
            itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                persistentDataContainer = itemMeta.getPersistentDataContainer();
            } else {
                persistentDataContainer = null;
            }
        } else {
            itemMeta = null;
            persistentDataContainer = null;
        }
    }

    public ItemStack getItemStack() {
        if (itemMeta != null && persistentDataContainer != null) {
            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }

    public void set(String keyName, Object value) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            String simpleName = value.getClass().getSimpleName();
            switch (simpleName) {
                case "Byte": {
                    persistentDataContainer.set(namespacedKey, PersistentDataType.BYTE, (byte) value);
                    break;
                }
                case "Double":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.DOUBLE, (double) value);
                    break;
                case "Float":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.FLOAT, (float) value);
                    break;
                case "Long":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.LONG, (long) value);
                    break;
                case "Integer":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.INTEGER, (int) value);
                    break;
                case "Short":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.SHORT, (short) value);
                    break;
                case "String":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.STRING, (String) value);
                    break;
                case "Boolean":
                    persistentDataContainer.set(namespacedKey, PersistentDataType.STRING, DataType.BOOLEAN + ":" + value);
                    break;
                default:
                    persistentDataContainer.set(namespacedKey, PersistentDataType.STRING, DataType.UNKNOWN + ":" + value);
                    break;
            }
        }
    }

    public DataType getDataType(String keyName) {
        if (isByte(keyName)) {
            return DataType.BYTE;
        } else if (isDouble(keyName)) {
            return DataType.DOUBLE;
        } else if (isFloat(keyName)) {
            return DataType.FLOAT;
        } else if (isLong(keyName)) {
            return DataType.LONG;
        } else if (isInteger(keyName)) {
            return DataType.INTEGER;
        } else if (isShort(keyName)) {
            return DataType.SHORT;
        } else if (isString(keyName)) {
            return DataType.STRING;
        } else if (isBoolean(keyName)) {
            return DataType.BOOLEAN;
        } else {
            return DataType.UNKNOWN;
        }
    }

    public Object get(String keyName) {
        DataType dataType = getDataType(keyName);
        if (dataType.equals(DataType.BYTE)) {
            return getByte(keyName);
        } else if (dataType.equals(DataType.DOUBLE)) {
            return getDouble(keyName);
        } else if (dataType.equals(DataType.FLOAT)) {
            return getFloat(keyName);
        } else if (dataType.equals(DataType.LONG)) {
            return getLong(keyName);
        } else if (dataType.equals(DataType.INTEGER)) {
            return getInteger(keyName);
        } else if (dataType.equals(DataType.SHORT)) {
            return getShort(keyName);
        } else if (dataType.equals(DataType.STRING)) {
            return getString(keyName);
        } else if (dataType.equals(DataType.BOOLEAN)) {
            return getBoolean(keyName);
        } else {
            return null;
        }
    }

    public boolean contains(String keyName) {
        return getKeys().contains(keyName);
    }

    public boolean isByte(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            return persistentDataContainer.has(namespacedKey, PersistentDataType.BYTE);
        }
        return false;
    }

    public byte getByte(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            Byte value = persistentDataContainer.get(namespacedKey, PersistentDataType.BYTE);
            if (value != null) {
                return value;
            }
        }
        return -1;
    }

    public boolean isDouble(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            return persistentDataContainer.has(namespacedKey, PersistentDataType.DOUBLE);
        }
        return false;
    }

    public double getDouble(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            Double value = persistentDataContainer.get(namespacedKey, PersistentDataType.DOUBLE);
            if (value != null) {
                return value;
            }
        }
        return -1.0d;
    }

    public boolean isFloat(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            return persistentDataContainer.has(namespacedKey, PersistentDataType.FLOAT);
        }
        return false;
    }

    public float getFloat(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            Float value = persistentDataContainer.get(namespacedKey, PersistentDataType.FLOAT);
            if (value != null) {
                return value;
            }
        }
        return -1.0f;
    }

    public boolean isLong(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            return persistentDataContainer.has(namespacedKey, PersistentDataType.LONG);
        }
        return false;
    }

    public long getLong(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            Long value = persistentDataContainer.get(namespacedKey, PersistentDataType.LONG);
            if (value != null) {
                return value;
            }
        }
        return -1L;
    }

    public boolean isInteger(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            return persistentDataContainer.has(namespacedKey, PersistentDataType.INTEGER);
        }
        return false;
    }

    public int getInteger(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            Integer value = persistentDataContainer.get(namespacedKey, PersistentDataType.INTEGER);
            if (value != null) {
                return value;
            }
        }
        return -1;
    }

    public boolean isShort(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            return persistentDataContainer.has(namespacedKey, PersistentDataType.SHORT);
        }
        return false;
    }

    public short getShort(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            Short value = persistentDataContainer.get(namespacedKey, PersistentDataType.SHORT);
            if (value != null) {
                return value;
            }
        }
        return -1;
    }

    public boolean isString(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            if (persistentDataContainer.has(namespacedKey, PersistentDataType.STRING)) {
                String value = persistentDataContainer.get(namespacedKey, PersistentDataType.STRING);
                if (value != null) {
                    return !value.startsWith(DataType.BOOLEAN + ":");
                }
            }
        }
        return false;
    }

    public String getString(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            String value = persistentDataContainer.get(namespacedKey, PersistentDataType.STRING);
            if (value != null) {
                return value;
            }
        }
        return "";
    }

    public boolean isBoolean(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            if (persistentDataContainer.has(namespacedKey, PersistentDataType.STRING)) {
                String value = persistentDataContainer.get(namespacedKey, PersistentDataType.STRING);
                if (value != null) {
                    return value.startsWith(DataType.BOOLEAN + ":");
                }
            }
        }
        return false;
    }

    public boolean getBoolean(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            if (persistentDataContainer.has(namespacedKey, PersistentDataType.STRING)) {
                String value = persistentDataContainer.get(namespacedKey, PersistentDataType.STRING);
                if (value != null) {
                    return Boolean.parseBoolean(value.split(":")[1]);
                }
            }
        }
        return false;
    }

    public void remove(String keyName) {
        NamespacedKey namespacedKey = NamespacedKey.fromString(keyName, javaPlugin);
        if (persistentDataContainer != null && namespacedKey != null) {
            persistentDataContainer.remove(namespacedKey);
        }
    }

    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        if (itemStack != null) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            if (itemMeta != null) {
                PersistentDataContainer persistentDataContainer = itemMeta.getPersistentDataContainer();
                for (NamespacedKey namespacedKey : persistentDataContainer.getKeys()) {
                    keys.add(namespacedKey.getNamespace() + ":" + namespacedKey.getKey());
                }
            }
        }
        return keys;
    }

}
