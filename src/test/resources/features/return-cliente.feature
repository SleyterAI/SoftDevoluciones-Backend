Feature: Return Client API

  Background:
    * url baseUrl
    * header Authorization = 'Bearer ' + token
    * header Content-Type = 'application/json'


# ============================================================
# POST /api/return
# ============================================================

  Scenario: Crear una solicitud de devolución correctamente

    Given path 'api/return'

    And request
    """
    {
        "orderId": 1,
        "productId": 1,
        "quantity": 1,
        "reason": "Producto defectuoso"
    }
    """

    When method POST
    Then status 200

    And match response == '#object'


  Scenario: Crear devolución con cantidad inválida

    Given path 'api/return'

    And request
    """
    {
        "orderId": 1,
        "productId": 1,
        "quantity": 0,
        "reason": "Producto defectuoso"
    }
    """

    When method POST
    Then status 400


  Scenario: Crear devolución sin motivo

    Given path 'api/return'

    And request
    """
    {
        "orderId": 1,
        "productId": 1,
        "quantity": 1
    }
    """

    When method POST
    Then status 400


  Scenario: Crear devolución sin orderId

    Given path 'api/return'

    And request
    """
    {
        "productId": 1,
        "quantity": 1,
        "reason": "Producto defectuoso"
    }
    """

    When method POST
    Then status 400


  Scenario: Crear devolución sin productId

    Given path 'api/return'

    And request
    """
    {
        "orderId": 1,
        "quantity": 1,
        "reason": "Producto defectuoso"
    }
    """

    When method POST
    Then status 400


  Scenario: Crear devolución con body vacío

    Given path 'api/return'

    And request
    """
    {}
    """

    When method POST
    Then status 400


# ============================================================
# GET /api/return/my-returns
# ============================================================

  Scenario: Obtener mis devoluciones

    Given path 'api/return/my-returns'

    When method GET
    Then status 200

    And match response == '#[]'


  Scenario: Obtener mis devoluciones sin autenticación

    * remove header Authorization

    Given path 'api/return/my-returns'

    When method GET
    Then status 401


# ============================================================
# GET /api/return/{id}
# ============================================================

  Scenario: Obtener devolución existente por ID

    * def returnId = 1

    Given path 'api/return', returnId

    When method GET
    Then status 200

    And match response == '#object'


  Scenario: Obtener devolución inexistente

    * def returnId = 999999

    Given path 'api/return', returnId

    When method GET
    Then status 400


  Scenario: Obtener devolución con ID inválido

    Given path 'api/return', 'abc'

    When method GET
    Then status 400


  Scenario: Obtener devolución sin autenticación

    * remove header Authorization

    Given path 'api/return', 1

    When method GET
    Then status 401