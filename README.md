# Cyclone Terminal

Open Source code for an eclipse compatible VTxxx style terminal emulator.

This is a work in progress but aims to provide an eclipse plugin that allows an 
application to create a VT52/100 etc view and interact with it.

## Usage
Users should create a terminal class that extends the VTTerminal abstract class and 
implement any required methods. Interaction with the terminal would be performed via 
the users terminal class.

## Features
The terminal allows for the following:
- The embedded 'fonts' will scale as the view housing the terminal is resized
- The display of LED's as on a real VTxxx,
- A 'DataScope' which when activated will present a separate window that displays
  all data sent to and received from the terminal with any non-printable characters
  shown using codes e.g. 'CR' for carriage return. A SimpleDataScopeDialog class
  has been provided with this emulator. However, if something different is required then
  simply creating a class which implements the DataScope interface and then calling 
  'SetDatascope' on the terminal with the required datascope will activate it, setting a null
  DataScope will deactivate any prior DataScope. 
  No DataScope is the default state fo a Terminal instance.

## Known Deficiencies
- The fonts embedded are not complete (and maybe not correct) for all the VT control sets.
  There are classes that will generate the code for creating the images used for each character, 
  these can be found in the com.cyclone.terminal.emulator.font.vt100 package
- Not all escape sequencies have been implemented (just enough for our project(s) to get by).
  However, we have endeavoured to allow as much of the [Vttest](https://invisible-island.net/vttest/#synopsis) 
  utility to run as we need.

### Contributions
Any Contributions, Fixes and/or Enhancements to this emulator are welcome.

###Acknowledgements
The state machine parser used in this project is a conversion from the parser presented at:

- [A parser for DEC’s ANSI-compatible video terminals](https://www.vt100.net/emu/dec_ansi_parser)


