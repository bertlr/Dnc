/*
 * Copyright (C) 2014 by Herbert Roider <herbert.roider@utanet.at>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.roiderh.dnc;
import jssc.SerialPort;
import java.util.UUID;

/**
 *
 * @author Herbert Roider <herbert.roider@utanet.at>
 */
public class Properties {
        static int LINEBREAK_CRLF = 0;
        static int LINEBREAK_LF = 1;
        static int LINEBREAK_CR = 2;
       
        public static int FLOWCONTROL_NONE = SerialPort.FLOWCONTROL_NONE;
        public static int FLOWCONTROL_RTSCTS = SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT;
        public static int FLOWCONTROL_XONXOFF = SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT;
        public static int FLOWCONTROL_RTXCTSXONXOFF = SerialPort.FLOWCONTROL_XONXOFF_IN | SerialPort.FLOWCONTROL_XONXOFF_OUT | SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT;
       
        public UUID uid = UUID.randomUUID();
        public String name = new String();
        public String port = new String();
       
        /**
         * Constants from jssc.SerialPort:
         */
        public Integer baud = SerialPort.BAUDRATE_9600;
        public Integer parity = SerialPort.PARITY_NONE;
        public Integer databits = SerialPort.DATABITS_8;
        public Integer stopbits = SerialPort.STOPBITS_1;
        public Integer flowcontrol = Properties.FLOWCONTROL_NONE;
        
        
        public Integer linebreak = Properties.LINEBREAK_CRLF;
        @Override
        public String toString(){
                return this.name;
        }
        

}
