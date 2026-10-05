Feature: Return Admin API

  Background:
    * url baseUrl
    * header Authorization = 'Bearer ' + token
    * header Content-Type = 'application/json'


# ============================================================
# GET ALL RETURNS
# ============================================================

  Scenario: Obtener todas las devoluciones como administrador

    Given path 'api/admin/return'
    When method GET
    Then status 200

    And match response == '#[]'


# ============================================================
# GET RETURN BY ID
# ============================================================

  Scenario: Obtener devolución existente por ID

    * def returnId = 1

    Given path 'api/admin/return', returnId
    When method GET
    Then status 200

    And match response == '#object'


  Scenario: Obtener devolución inexistente

    * def returnId = 999999

    Given path 'api/admin/return', returnId
    When method GET
    Then status 404


  Scenario: Obtener devolución con ID inválido

    Given path 'api/admin/return', 'abc'
    When method GET
    Then status 400


# ============================================================
# FILTER
# ============================================================

  Scenario: Obtener devoluciones sin filtros

    Given path 'api/admin/return/filter'
    When method GET
    Then status 200

    And match response == '#[]'


  Scenario: Filtrar devoluciones por status

    Given path 'api/admin/return/filter'
    And param status = 'PENDING'
    When method GET
    Then status 200

    And match response == '#[]'


  Scenario: Filtrar devoluciones por fecha inicial

    Given path 'api/admin/return/filter'
    And param fromDate = '01-10-2026'
    When method GET
    Then status 200

    And match response == '#[]'


  Scenario: Filtrar devoluciones por fecha final

    Given path 'api/admin/return/filter'
    And param toDate = '02-10-2026'
    When method GET
    Then status 200

    And match response == '#[]'


  Scenario: Filtrar por status y rango de fechas

    Given path 'api/admin/return/filter'
    And param status = 'PENDING'
    And param fromDate = '01-10-2026'
    And param toDate = '02-10-2026'
    When method GET
    Then status 200

    And match response == '#[]'


  Scenario: Filtrar con formato de fecha incorrecto

    Given path 'api/admin/return/filter'
    And param fromDate = '2026-10-01'
    When method GET
    Then status 400


  Scenario: Acceder a devoluciones admin sin token

    * remove header Authorization

    Given path 'api/admin/return'
    When method GET
    Then status 401


  Scenario: Actualizar notas con valor vacío

    * def returnId = 1

    Given path 'api/admin/return', returnId, 'notes'

    And request
    """
    {
        "notes": ""
    }
    """

    When method PATCH
    Then status 400


  Scenario: Actualizar notas de devolución inexistente

    * def returnId = 999999

    Given path 'api/admin/return', returnId, 'notes'

    And request
    """
    {
        "notes": "Nota de prueba"
    }
    """

    When method PATCH
    Then status 400


  Scenario: Actualizar notas del operador

    * def returnId = 1

    Given path 'api/admin/return', returnId, 'notes'

    And request
    """
    {
        "notes": "Producto revisado por el operador QA"
    }
    """

    When method PATCH
    Then status 200

    And match response contains 'operator notes:'

  Scenario: Actualizar estado de una devolución

    * def returnId = 1

    Given path 'api/admin/return', returnId, 'status'

    And request
    """
    {
        "status": "APPROVED"
    }
    """

    When method PATCH
    Then status 200

    And match response contains 'Return Id:'
    And match response contains 'APPROVED'