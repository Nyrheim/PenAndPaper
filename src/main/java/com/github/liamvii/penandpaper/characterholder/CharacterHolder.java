package com.github.liamvii.penandpaper.characterholder;

import com.github.liamvii.penandpaper.Pen;
import com.github.liamvii.penandpaper.utils.ConnectionManager;
import com.github.liamvii.penandpaper.utils.generated.tables.Characterholders;
import org.bukkit.entity.Player;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.ArrayList;

import static com.github.liamvii.penandpaper.utils.generated.Tables.CHARACTERHOLDERS;
import static org.jooq.SQLDialect.MYSQL;

public class CharacterHolder {

    String uniqueID;

    //
    public CharacterHolder(Player player) {
        uniqueID = player.getUniqueId().toString();
        DSLContext write = DSL.using(ConnectionManager.getWrite(), MYSQL);
        if (!(write.fetchExists(write.selectOne().from(CHARACTERHOLDERS).where(CHARACTERHOLDERS.UUID.eq(uniqueID))))) {
            write.insertInto(CHARACTERHOLDERS, CHARACTERHOLDERS.UUID, CHARACTERHOLDERS.DUTYMODE).values(uniqueID, "false");
        }
    }

}