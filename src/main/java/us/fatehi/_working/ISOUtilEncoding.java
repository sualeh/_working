/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2008 Alejandro P. Revilla
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

// package org.jpos.iso;
package us.fatehi._working;


import java.nio.charset.StandardCharsets;

import org.jpos.iso.ISOUtil;

public class ISOUtilEncoding
{

  public static void main(String[] args)
  {

    // See
    // http://www-01.ibm.com/support/knowledgecenter/SSEPEK_10.0.0/com.ibm.db2z10.doc.char/src/tpc/db2z_codeptdiffebcdic.dita

    final char[] chars = new char[] {
        '[', ']', '^', '¢', '|', '¨', 'Ý'
    };

    final byte[] ebcdicBytes = ISOUtil.asciiToEbcdic(new String(chars)
      .getBytes(StandardCharsets.ISO_8859_1));
    for (char c: chars)
    {
      System.out.print(c);
      System.out.print("  ");
    }
    System.out.println();

    for (byte b: ebcdicBytes)
    {
      System.out.print(String.format("%02x ", b).toUpperCase());
    }
    System.out.println();
  }

}
