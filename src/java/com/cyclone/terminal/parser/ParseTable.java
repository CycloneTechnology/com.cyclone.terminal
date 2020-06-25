package com.cyclone.terminal.parser;

/**
 * @author Phil.Baxter
 * 
 */
public interface ParseTable
{
    /**
     * The from and To are or'ed together in the following array, with the to being left shifted by 4 bits before the or operation.
     *  
     *  State.From State.To Action.Action
     *  
     *  ANYWHERE, GROUND, EXECUTE, method()
     *  
     *  FIXME - This could all be done in a much cleaner type safe way!
     */
    int[][] STATE_TABLE =
    {
            {
                    // ANYWHERE
                    0, // 0
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 10
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 20
                    0,
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    0,
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.NONE.ordinal() | (State.ESCAPE.ordinal() << 4),
                    0,
                    0,
                    0, // 30
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 40
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 50
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 60
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 70
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 80
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 90
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 100
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 110
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0, // 120
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4), // 130
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4), // 140
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.NONE.ordinal() | (State.DCS_ENTRY.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4), // 150
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.NONE.ordinal()
                            | (State.SOS_PM_APC_STRING.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.NONE.ordinal() | (State.CSI_ENTRY.ordinal() << 4),
                    Action.EXECUTE.ordinal() | (State.GROUND.ordinal() << 4),
                    Action.NONE.ordinal() | (State.OSC_STRING.ordinal() << 4),
                    Action.NONE.ordinal()
                            | (State.SOS_PM_APC_STRING.ordinal() << 4),
                    Action.NONE.ordinal()
                            | (State.SOS_PM_APC_STRING.ordinal() << 4), 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // CSI_ENTRY
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4), // 40
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4), // 50
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.COLLECT.ordinal() | (State.CSI_PARAM.ordinal() << 4), // 60
                    Action.COLLECT.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.COLLECT.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.COLLECT.ordinal() | (State.CSI_PARAM.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 70
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 80
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 90
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 100
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 110
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 120
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // CSI_IGNORE
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 40
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 50
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 60
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4), // 70
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4), // 80
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4), // 90
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4), // 100
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4), // 110
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4), // 120
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    0 | (State.GROUND.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // CSI_INTERMEDIATE
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4), // 40
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4), // 50
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4), // 60
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 70
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 80
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 90
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 100
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 110
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 120
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // CSI_PARAM
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4), // 40
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.CSI_INTERMEDIATE.ordinal() << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4), // 50
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4), // 60
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    0 | (State.CSI_IGNORE.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 70
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 80
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 90
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 100
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 110
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 120
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.CSI_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // DCS_ENTRY
                    Action.IGNORE.ordinal() | (0 << 4), // 0
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 10
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 20
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 30
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4), // 40
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4), // 50
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    Action.PARAM.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.COLLECT.ordinal() | (State.DCS_PARAM.ordinal() << 4), // 60
                    Action.COLLECT.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.COLLECT.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    Action.COLLECT.ordinal() | (State.DCS_PARAM.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 70
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 80
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 90
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 100
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 110
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 120
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // DCS_IGNORE
                    Action.IGNORE.ordinal() | (0 << 4), // 0
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 10
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 20
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 30
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 40
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 50
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 60
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 70
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 80
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 90
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 100
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 110
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 120
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0,
                    0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 150
                    0, 0, 0, 0, 0, 0 | (State.GROUND.ordinal() << 4), 0, 0, 0,
                    0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // DCS_INTERMEDIATE
                    Action.IGNORE.ordinal() | (0 << 4), // 0
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 10
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 20
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 30
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4), // 40
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4), // 50
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4), // 60
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 70
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 80
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 90
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 100
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 110
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 120
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // DCS_PARAM
                    Action.IGNORE.ordinal() | (0 << 4), // 0
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 10
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 20
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 30
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4), // 40
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.DCS_INTERMEDIATE.ordinal() << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4), // 50
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    Action.PARAM.ordinal() | (0 << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4), // 60
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_IGNORE.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 70
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 80
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 90
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 100
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 110
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4), // 120
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    0 | (State.DCS_PASSTHROUGH.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // DCS_PASSTHROUGH
                    Action.PUT.ordinal() | (0 << 4), // 0
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 10
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 20
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    0,
                    Action.PUT.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 30
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 40
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 50
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 60
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 70
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 80
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 90
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 100
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 110
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4), // 120
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.PUT.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0,
                    0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 150
                    0, 0, 0, 0, 0, 0 | (State.GROUND.ordinal() << 4), 0, 0, 0,
                    0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // ESCAPE
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4), // 40
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.COLLECT.ordinal()
                            | (State.ESCAPE_INTERMEDIATE.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 50
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 60
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 70
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    0 | (State.DCS_ENTRY.ordinal() << 4), // 80
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    0 | (State.SOS_PM_APC_STRING.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 90
                    0 | (State.CSI_ENTRY.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    0 | (State.OSC_STRING.ordinal() << 4),
                    0 | (State.SOS_PM_APC_STRING.ordinal() << 4),
                    0 | (State.SOS_PM_APC_STRING.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 100
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 110
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 120
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // ESCAPE_INTERMEDIATE
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4), // 40
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.COLLECT.ordinal() | (0 << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 50
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 60
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 70
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 80
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 90
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 100
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 110
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4), // 120
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.ESC_DISPATCH.ordinal()
                            | (State.GROUND.ordinal() << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0, 0, 0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 150
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // GROUND
                    Action.EXECUTE.ordinal() | (0 << 4), // 0
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 10
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 20
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 30
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 40
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 50
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 60
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 70
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 80
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 90
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 100
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 110
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4), // 120
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.PRINT.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 130
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 140
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    0,
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), // 150
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4),
                    Action.EXECUTE.ordinal() | (0 << 4), 0,
                    Action.EXECUTE.ordinal() | (0 << 4), 0, 0, 0, 0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // OSC_STRING
                    Action.IGNORE.ordinal() | (0 << 4), // 0
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 10
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 20
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 30
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 40
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 50
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 60
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 70
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 80
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 90
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 100
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 110
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), // 120
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4),
                    Action.OSC_PUT.ordinal() | (0 << 4), 0, 0,
                    0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 150
                    0, 0, 0, 0, 0, 0 | (State.GROUND.ordinal() << 4), 0, 0, 0,
                    0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            },
            {
                    // SOS_PM_APC_STRING
                    Action.IGNORE.ordinal() | (0 << 4), // 0
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 10
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 20
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    0,
                    0,
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 30
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 40
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 50
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 60
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 70
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 80
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 90
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 100
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 110
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), // 120
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4),
                    Action.IGNORE.ordinal() | (0 << 4), 0,
                    0, // 130
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 140
                    0, 0, 0, 0, 0, 0, 0, 0, 0,
                    0, // 150
                    0, 0, 0, 0, 0, 0, 0 | (State.GROUND.ordinal() << 4), 0, 0,
                    0, // 160
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 180
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 190
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 200
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 210
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 220
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 230
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 240
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 250
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 260
            }};

    /**
     * 
     */
    Action[] ENTRY_ACTIONS =
    {Action.NONE, // none for ANYWHERE
            Action.CLEAR, // CSI_ENTRY
            Action.NONE, // none for CSI_IGNORE
            Action.NONE, // none for CSI_INTERMEDIATE
            Action.NONE, // none for CSI_PARAM
            Action.CLEAR, // DCS_ENTRY
            Action.NONE, // none for DCS_IGNORE
            Action.NONE, // none for DCS_INTERMEDIATE
            Action.NONE, // none for DCS_PARAM
            Action.HOOK, // DCS_PASSTHROUGH
            Action.CLEAR, // ESCAPE
            Action.NONE, // none for ESCAPE_INTERMEDIATE
            Action.NONE, // none for GROUND
            Action.OSC_START, // OSC_STRING
            Action.NONE // none for SOS_PM_APC_STRING
    };

    /**
     * 
     */
    Action[] EXIT_ACTIONS =
    {Action.NONE, // none for ANYWHERE
            Action.NONE, // none for CSI_ENTRY
            Action.NONE, // none for CSI_IGNORE
            Action.NONE, // none for CSI_INTERMEDIATE
            Action.NONE, // none for CSI_PARAM
            Action.NONE, // none for DCS_ENTRY
            Action.NONE, // none for DCS_IGNORE
            Action.NONE, // none for DCS_INTERMEDIATE
            Action.NONE, // none for DCS_PARAM
            Action.UNHOOK, // DCS_PASSTHROUGH
            Action.NONE, // none for ESCAPE
            Action.NONE, // none for ESCAPE_INTERMEDIATE
            Action.NONE, // none for GROUND
            Action.OSC_END, // OSC_STRING
            Action.NONE // none for SOS_PM_APC_STRING
    };

}
