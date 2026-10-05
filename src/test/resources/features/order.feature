Feature: Order API

  Background:
    * url baseUrl
    * header Authorization = 'Bearer ' + token


# ============================================================
# GET ALL ORDERS
# ============================================================

  Scenario: Obtener todas las órdenes

    Given path 'api/order'
    When method GET
    Then status 200

    And match response == '#[]'


# ============================================================
# GET ORDER BY ID
# ============================================================

  Scenario: Obtener una orden existente

    * def orderId = 1

    Given path 'api/order', orderId
    When method GET
    Then status 200

    And match response.order_id == orderId


  Scenario: Obtener una orden inexistente

    * def orderId = 999999

    Given path 'api/order', orderId
    When method GET
    Then status 400


  Scenario: Obtener orden con ID inválido

    Given path 'api/order', 'abc'
    When method GET
    Then status 400


# ============================================================
# MY ORDERS
# ============================================================

  Scenario: Obtener mis órdenes

    Given path 'api/order/my-orders'
    When method GET
    Then status 200

    And match response == '#[]'


# ============================================================
# ORDER DETAIL
# ============================================================

  Scenario: Obtener detalle de producto de una orden

    * def orderId = 1
    * def productId = 1

    Given path 'api/order/product', orderId, productId
    When method GET
    Then status 200

    And match response == '#object'


  Scenario: Obtener detalle con orderId inválido

    Given path 'api/order/product', 'abc', 1
    When method GET
    Then status 400


  Scenario: Obtener detalle con productId inválido

    Given path 'api/order/product', 1, 'abc'
    When method GET
    Then status 400
