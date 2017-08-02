/*! ******************************************************************************
  *
  * Pentaho Data Integration
  *
  * Copyright (C) 2002-2017 by Pentaho : http://www.pentaho.com
  *
  *******************************************************************************
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with
  * the License. You may obtain a copy of the License at
  *
  *    http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software
  * distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  ******************************************************************************/

package org.pentaho.di.sdk.samples.carte;

import org.pentaho.di.core.Const;
import org.pentaho.di.www.StartJobServlet;

public class StartJobSample extends AbstractSample {
  public static void main( String[] args ) throws Exception {
    if ( args.length < 5 ) {
      System.out.println( " You must specify the following parameters Carte_host Carte_port "
        + "Carte_login Carte_password job_name" );
      System.out.println( " For example 127.0.0.1 8088 cluster cluster dummy_job" );
      System.exit( 1 );
    }
    init( args[ 0 ], Integer.parseInt( args[ 1 ] ), args[ 2 ], args[ 3 ] );
    // building target url
    String urlString = getUrlString( args[ 0 ], args[ 1 ], args[ 4 ] );

    //building auth token
    String auth = getAuthString( args[ 2 ], args[ 3 ] );

    String response = sendStartJobRequest( urlString, auth );
    if ( response != null ) {
      System.out.println( "Server response:" );
      System.out.println( response );
    }
  }

  public static String sendStartJobRequest( String urlString, String authentication ) throws Exception {
    String message = "Error occurred during starting job.";
    return sendGetStatusRequest( urlString, host, port, user, password, message );
  }

  public static String getUrlString( String realHostname, String port, String job_name ) {
    String urlString = "http://" + realHostname + ":" + port
      + StartJobServlet.CONTEXT_PATH + "/?xml=Y&name=" + job_name;
    urlString = Const.replace( urlString, " ", "%20" );
    return urlString;
  }

}
