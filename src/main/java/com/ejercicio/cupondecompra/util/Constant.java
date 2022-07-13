package com.ejercicio.cupondecompra.util;

public final class Constant {

    private Constant() {
    }

    public static final String ATTRIBUTES_PARAM = "id,title,price,site_id";
    public static final String IDS_QUERY_PARAM = "ids";
    public static final String ATTRIBUTES_QUERY_PARAM = "attributes";
    public static final String DATABASE_NAME = "${spring.data.mongodb.database}";
    public static final String PATH_REQUEST_MAPPING = "${rest.controller.path.principal}";
    public static final String PATH_GET_MAPPING_STATS = "${rest.controller.path.stats}";
    public static final String EXCEPTION_MESSAGE_DUPLICATED_ITEMS = "La petición contiene item_ids duplicados";
    public static final String EXCEPTION_MESSAGE_DUPLICATED_ITEMS_DETAIL = "Los item_ids duplicados son: ";
    public static final String EXCEPTION_MESSAGE_NO_ITEMS_FOUND = "El monto es menor a cualquier costo de los items ingresados o no existen";
    public static final String EXCEPTION_MESSAGE_MONGODB_ERROR = "Error en MongoDB, ";
    public static final String EXCEPTION_MESSAGE_API_ML_ERROR = "Error al consumir el API de Mercado Libre, ";
    public static final String EXCEPTION_MESSAGE_GETTING_FAVORITES_ITEM_MONGODB_ERROR = "Error en MongoDB, ";
    public static final String EXCEPTION_MESSAGE_GETTING_OPTIMUS_TOTAL_ERROR = "Error al calcular el total a gastar más óptimo, ";
    public static final String LOG_MESSAGE_START_GET_THE_MAXIMUM_SPEND_POSIBLE = "Inicia el procesamiento de obtener el gasto máximo posible, Request={}";
    public static final String LOG_MESSAGE_END_GET_THE_MAXIMUM_SPEND_POSIBLE = "Finaliza el procesamiento de obtener el gasto máximo posible, Response={}";
    public static final String LOG_MESSAGE_START_GET_TOP_FAVORITES = "Inicia el proceso de consulta para obtener el top de favoritos Mercado Libre";
    public static final String LOG_MESSAGE_END_GET_TOP_FAVORITES = "Finaliza el proceso de consulta para obtener el top de favoritos Mercado Libre";
    public static final String LOG_MESSAGE_GET_FAVORITES_ITEM = "---Se obtienen los items favoritos---";
    public static final String LOG_MESSAGE_START_GETTING_ITEMS_FROM_ML_API = "Inicia el proceso para obtener los items del API de Mercado Libre";
    public static final String LOG_MESSAGE_END_GETTING_ITEMS_FROM_ML_API = "Finaliza el proceso para obtener los items del API de Mercado Libre";
    public static final String LOG_MESSAGE_START_GETTING_OPTIMUS_TOTAL = "Inicia el proceso para calcular el total a gastar más óptimo";
    public static final String LOG_MESSAGE_END_GETTING_OPTIMUS_TOTAL = "Finaliza el proceso para calcular el total a gastar más óptimo";
    public static final String LOG_MESSAGE_FINDING_ITEM_BY_ID_ITEM = "Se busca y obtiene el item={}";
    public static final String LOG_MESSAGE_INSERTING_ITEM = "Se inserta el nuevo item={}";
    public static final String LOG_MESSAGE_ITEM_QUANTITY_UPDATED = "Se actualiza la propiedad quantity del item={}, antes: {}, ahora: {}";
    public static final String BASE_URL_MERCADO_LIBRE = "${api.ml.url.base}";
    public static final String NUMBER_TOP_FAVORITES = "${rest.controller.top-favorites}";
    public static final String ITEMS_URL_MERCADO_LIBRE = "${api.ml.url.items}";
    public static final String ITEM_ID_FIELD = "itemId";
    public static final String QUANTITY_FIELD = "quantity";
    public static final String MONGO_ID_FIELD = "_id";
    public static final String TITLE_FIELD = "title";
    public static final String PRICE_FIELD = "price";
    public static final String SITE_ID_FIELD = "siteId";
    public static final String ID_JSON_PROPERTY_VALUE = "id";
    public static final String SITE_ID_JSON_PROPERTY_VALUE = "site_id";
    public static final String ITEM_IDS_JSON_PROPERTY_VALUE = "item_ids";
    public static final String ITEMS_DOCUMENT = "items";
    public static final int ID_JSON_PROPERTY_INDEX = 1;
    public static final int ITEM_IDS_JSON_PROPERTY_INDEX = 1;
    public static final String COLON_PLUS_SPACE = ": ";
    public static final String OPEN_SQUARE_BRACKET = "[";
    public static final String CLOSED_SQUARE_BRACKET = "]";
    public static final String EMPTY_STRING = " ";
    public static final String BLANK_STRING = "";
    public static final String DETAIL_NOT_FOUND_ITEMS_AT_ML_API_MESSAGE = "El precio de cada uno de los item_ids de la petición supera el monto que se ingresó o no existen en la API de Mercado Libre";
    public static final String STATUS_400_CODE = "400";
    public static final String NOT_NULL_ITEM_IDS_REQUEST_MESSAGE = "La propiedad item_ids no debe ser nula";
    public static final String NOT_EMPTY_ITEM_IDS_REQUEST_MESSAGE = "La propiedad item_ids no debe estar vacía";
    public static final String NOT_NULL_AMOUNT_REQUEST_MESSAGE = "La propiedad amount no debe ser nula";

}
