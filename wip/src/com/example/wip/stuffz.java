/*public static JSONObject getLocationInfo(String address) {

        HttpGet httpGet = new HttpGet("http://maps.google.com/maps/api/geocode/json?address=" +address+"&ka&sensor=false");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return jsonObject;
    }

    public static GeoPoint getGeoPoint(JSONObject jsonObject) {

        Double lon = new Double(0);
        Double lat = new Double(0);

        try {

            lon = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lng");

            lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                .getJSONObject("geometry").getJSONObject("location")
                .getDouble("lat");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));

    }


GeoPoint srcGeoPoint =getGeoPoint(getLocationInfo(fromAddress.replace("\n"," ").replace(" ", "%20")));
            GeoPoint destGeoPoint =getGeoPoint(getLocationInfo(CalDescription.toAddress.replace("\n"," ").replace(" ", "%20")));
            */